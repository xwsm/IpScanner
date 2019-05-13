package scanner.wifi.wifiscanner;

import android.accounts.Account;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Head 2222222 分支更新
 */
public class MainActivity extends AppCompatActivity {

    Map<String, String> xData;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CompanyEntity cData = CompanyEntity.objectFromData(getFromAssets("company.json"));
        initRv();
        getIpData();
        xData = getIdNameMap(cData.getData());

//        Toast.makeText(this, "" + xData.size(), Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Map<String, String> getIdNameMap(List<CompanyEntity.DataBean> accounts) {
        return accounts.stream().collect(Collectors.toMap(CompanyEntity.DataBean::getMac, CompanyEntity.DataBean::getCompany,
                (value1, value2) -> {
                    return value1;
                }));
    }

    @BindView(R.id.rv)
    RecyclerView rv;
    BaseQuickAdapter<ipData.Data, BaseViewHolder> adapter;
    List<ipData.Data> data = new ArrayList<>();

    void initRv() {
        adapter = new BaseQuickAdapter<ipData.Data, BaseViewHolder>(R.layout.activity_main_item, data) {
            @Override
            protected void convert(BaseViewHolder helper, ipData.Data item) {
                ((TextView) helper.getView(R.id.ip)).setText(item.getIp());
                ((TextView) helper.getView(R.id.mac)).setText(item.getMac());
                ((TextView) helper.getView(R.id.company)).setText(item.getCompany());
            }
        };
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }

    void getIpData() {
        IpScanner ipScanner = new IpScanner();
        ipScanner.setOnScanListener(resultMap->{
            Log.i("macAddress", resultMap.toString());
            for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                ipData.Data cData = new ipData.Data();
                cData.setMac(key);
                cData.setIp(value);
                String key2 = key.replaceAll(":", "-");
                cData.setCompany(xData.get(key2.substring(0, 8).toUpperCase()));
                data.add(cData);
            }
            adapter.notifyDataSetChanged();
        });
        ipScanner.startScan();
    }

    public static class ipData {
        public List<Data> getDataList() {
            return dataList;
        }

        public void setDataList(List<Data> dataList) {
            this.dataList = dataList;
        }

        List<Data> dataList;

        public static class Data {
            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            String ip;
            String company;

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            String mac;
        }
    }


    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            String Result = "";
            while ((line = bufReader.readLine()) != null) {
                Result += line;
            }
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
