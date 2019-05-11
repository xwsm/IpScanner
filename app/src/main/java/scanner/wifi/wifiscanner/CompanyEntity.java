package scanner.wifi.wifiscanner;

import java.util.List;

/**
 * 2019 重庆指讯科技股份有限公司
 *
 * @author: Wsm
 * @date: 2019/5/11 15:08
 * @description:
 */
public class CompanyEntity {

    private List<DataBean> data;

    public static CompanyEntity objectFromData(String str) {

        return new com.google.gson.Gson().fromJson(str, CompanyEntity.class);
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mac : E0-43-DB
         * company : Shenzhen ViewAt Technology Co.,Ltd.
         */

        private String mac;
        private String company;

        public static DataBean objectFromData(String str) {

            return new com.google.gson.Gson().fromJson(str, DataBean.class);
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }
    }
}
