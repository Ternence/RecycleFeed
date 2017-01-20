package com.dianxinos.optimizer;

import java.util.List;

/**
 * Created by zhangtengyuan on 2017/1/16.
 */
public class RecommendDetailsBean {


    /**
     * response : {"datas":[{"isLocalCard":false,"content":"首页Feed流测试卡片内容","id":"181","title":"首页Feed流测试卡片","showStart":1483694891000,"interval":3600000,"count":100,"modifyTime":1483699736485,"cardType":403,"buttonText":"按钮","showEnd":4637294891000,"iconUrl":"http://sandbox.sjws.baidu.com:8080/cdn/server/sjws_omp/img/313e5/300_150_a4d19760bf023d0a.png"},{"isLocalCard":false,"content":"ceshi","id":"182","title":"测试","showStart":1484029673000,"interval":3600000,"count":1,"iconUrl":"http://sandbox.sjws.baidu.com:8080/cdn/server/sjws_omp/img/313e5/300_150_a4d19760bf023d0a.png","modifyTime":1484707462865,"cardType":304,"buttonText":"ces","showEnd":4637629673000},{"isLocalCard":false,"content":"首页 Feed卡片1  content","id":"183","title":"首页 Feed卡片1","showStart":1484029673000,"interval":0,"count":10000,"iconUrl":"http://sandbox.sjws.baidu.com:8080/cdn/server/sjws_omp/img/313e5/96_96_505f1f5f0a57228b.png","modifyTime":1484721077849,"cardType":403,"buttonText":"feed1","showEnd":4637629673000},{"isLocalCard":false,"content":"首页 Feed卡片1  content","id":"184","title":"首页 Feed卡片1","showStart":1484029673000,"interval":0,"count":10000,"modifyTime":1484721096578,"cardType":403,"buttonText":"feed1","showEnd":4637629673000}]}
     * responseHeader : {"errcode":200,"time":1484725613261,"message":"ok","version":"1.0"}
     */

    private ResponseBean response;
    private ResponseHeaderBean responseHeader;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public ResponseHeaderBean getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeaderBean responseHeader) {
        this.responseHeader = responseHeader;
    }

    public static class ResponseBean {
        private List<DatasBean> datas;

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * isLocalCard : false
             * content : 首页Feed流测试卡片内容
             * id : 181
             * title : 首页Feed流测试卡片
             * showStart : 1483694891000
             * interval : 3600000
             * count : 100
             * modifyTime : 1483699736485
             * cardType : 403
             * buttonText : 按钮
             * showEnd : 4637294891000
             * iconUrl : http://sandbox.sjws.baidu.com:8080/cdn/server/sjws_omp/img/313e5/300_150_a4d19760bf023d0a.png
             */

            public boolean isLocalCard;
            public String content;
            public String id;
            public String title;
            public long showStart;
            public int interval;
            public int count;
            public long modifyTime;
            public int cardType;
            public String buttonText;
            public long showEnd;
            public String iconUrl;

            public boolean isIsLocalCard() {
                return isLocalCard;
            }

            public void setIsLocalCard(boolean isLocalCard) {
                this.isLocalCard = isLocalCard;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getShowStart() {
                return showStart;
            }

            public void setShowStart(long showStart) {
                this.showStart = showStart;
            }

            public int getInterval() {
                return interval;
            }

            public void setInterval(int interval) {
                this.interval = interval;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public long getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(long modifyTime) {
                this.modifyTime = modifyTime;
            }

            public int getCardType() {
                return cardType;
            }

            public void setCardType(int cardType) {
                this.cardType = cardType;
            }

            public String getButtonText() {
                return buttonText;
            }

            public void setButtonText(String buttonText) {
                this.buttonText = buttonText;
            }

            public long getShowEnd() {
                return showEnd;
            }

            public void setShowEnd(long showEnd) {
                this.showEnd = showEnd;
            }

            public String getIconUrl() {
                return iconUrl;
            }

            public void setIconUrl(String iconUrl) {
                this.iconUrl = iconUrl;
            }
        }
    }

    public static class ResponseHeaderBean {
        /**
         * errcode : 200
         * time : 1484725613261
         * message : ok
         * version : 1.0
         */

        private int errcode;
        private long time;
        private String message;
        private String version;

        public int getErrcode() {
            return errcode;
        }

        public void setErrcode(int errcode) {
            this.errcode = errcode;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}