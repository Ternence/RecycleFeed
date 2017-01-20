package com.dianxinos.optimizer;

import java.util.List;

/**
 * Created by zhangtengyuan on 2017/1/16.
 */
public class RecommendBean {


    /**
     * response : {"datas":{"sydbyy":[{"isLocalCard":false,"id":"180","modifyTime":1483699722201,"sort":0},{"isLocalCard":false,"id":"186","modifyTime":1484721081981,"sort":1},{"isLocalCard":false,"id":"187","modifyTime":1484721092338,"sort":2}],"syfeed":[{"isLocalCard":false,"id":"181","modifyTime":1483699736485,"sort":0},{"isLocalCard":false,"id":"183","modifyTime":1484721077849,"sort":1},{"isLocalCard":false,"id":"184","modifyTime":1484721096578,"sort":2},{"isLocalCard":false,"id":"185","modifyTime":1484721099811,"sort":3}],"trashclean":[{"isLocalCard":false,"id":"133","modifyTime":1463557765295,"sort":5},{"isLocalCard":false,"id":"135","modifyTime":1463642570537,"sort":9},{"isLocalCard":false,"id":"109","modifyTime":1461630565542,"sort":28},{"isLocalCard":false,"id":"132","modifyTime":1464246242405,"sort":30},{"isLocalCard":false,"id":"134","modifyTime":1463642588739,"sort":33},{"isLocalCard":false,"id":"113","modifyTime":1464247497607,"sort":34},{"isLocalCard":false,"id":"148","modifyTime":1466058905946,"sort":36},{"isLocalCard":false,"id":"106","modifyTime":1470380943995,"sort":44}],"phoneacc":[{"isLocalCard":false,"id":"134","modifyTime":1463642588739,"sort":9},{"isLocalCard":false,"id":"110","modifyTime":1461630539278,"sort":35},{"isLocalCard":false,"id":"132","modifyTime":1464246242405,"sort":38},{"isLocalCard":false,"id":"133","modifyTime":1463557765295,"sort":39},{"isLocalCard":false,"id":"135","modifyTime":1463642570537,"sort":41}]}}
     * responseHeader : {"errcode":200,"time":1484721518534,"message":"ok","version":"1.0"}
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
        /**
         * datas : {"sydbyy":[{"isLocalCard":false,"id":"180","modifyTime":1483699722201,"sort":0},{"isLocalCard":false,"id":"186","modifyTime":1484721081981,"sort":1},{"isLocalCard":false,"id":"187","modifyTime":1484721092338,"sort":2}],"syfeed":[{"isLocalCard":false,"id":"181","modifyTime":1483699736485,"sort":0},{"isLocalCard":false,"id":"183","modifyTime":1484721077849,"sort":1},{"isLocalCard":false,"id":"184","modifyTime":1484721096578,"sort":2},{"isLocalCard":false,"id":"185","modifyTime":1484721099811,"sort":3}],"trashclean":[{"isLocalCard":false,"id":"133","modifyTime":1463557765295,"sort":5},{"isLocalCard":false,"id":"135","modifyTime":1463642570537,"sort":9},{"isLocalCard":false,"id":"109","modifyTime":1461630565542,"sort":28},{"isLocalCard":false,"id":"132","modifyTime":1464246242405,"sort":30},{"isLocalCard":false,"id":"134","modifyTime":1463642588739,"sort":33},{"isLocalCard":false,"id":"113","modifyTime":1464247497607,"sort":34},{"isLocalCard":false,"id":"148","modifyTime":1466058905946,"sort":36},{"isLocalCard":false,"id":"106","modifyTime":1470380943995,"sort":44}],"phoneacc":[{"isLocalCard":false,"id":"134","modifyTime":1463642588739,"sort":9},{"isLocalCard":false,"id":"110","modifyTime":1461630539278,"sort":35},{"isLocalCard":false,"id":"132","modifyTime":1464246242405,"sort":38},{"isLocalCard":false,"id":"133","modifyTime":1463557765295,"sort":39},{"isLocalCard":false,"id":"135","modifyTime":1463642570537,"sort":41}]}
         */

        private DatasBean datas;

        public DatasBean getDatas() {
            return datas;
        }

        public void setDatas(DatasBean datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            private List<SydbyyBean> sydbyy;
            private List<SyfeedBean> syfeed;
            private List<TrashcleanBean> trashclean;
            private List<PhoneaccBean> phoneacc;

            public List<SydbyyBean> getSydbyy() {
                return sydbyy;
            }

            public void setSydbyy(List<SydbyyBean> sydbyy) {
                this.sydbyy = sydbyy;
            }

            public List<SyfeedBean> getSyfeed() {
                return syfeed;
            }

            public void setSyfeed(List<SyfeedBean> syfeed) {
                this.syfeed = syfeed;
            }

            public List<TrashcleanBean> getTrashclean() {
                return trashclean;
            }

            public void setTrashclean(List<TrashcleanBean> trashclean) {
                this.trashclean = trashclean;
            }

            public List<PhoneaccBean> getPhoneacc() {
                return phoneacc;
            }

            public void setPhoneacc(List<PhoneaccBean> phoneacc) {
                this.phoneacc = phoneacc;
            }

            public static class SydbyyBean {
                /**
                 * isLocalCard : false
                 * id : 180
                 * modifyTime : 1483699722201
                 * sort : 0
                 */

                private boolean isLocalCard;
                private String id;
                private long modifyTime;
                private int sort;

                public boolean isIsLocalCard() {
                    return isLocalCard;
                }

                public void setIsLocalCard(boolean isLocalCard) {
                    this.isLocalCard = isLocalCard;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public long getModifyTime() {
                    return modifyTime;
                }

                public void setModifyTime(long modifyTime) {
                    this.modifyTime = modifyTime;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }
            }

            public static class SyfeedBean {
                /**
                 * isLocalCard : false
                 * id : 181
                 * modifyTime : 1483699736485
                 * sort : 0
                 */

                private boolean isLocalCard;
                private String id;
                private long modifyTime;
                private int sort;

                public boolean isIsLocalCard() {
                    return isLocalCard;
                }

                public void setIsLocalCard(boolean isLocalCard) {
                    this.isLocalCard = isLocalCard;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public long getModifyTime() {
                    return modifyTime;
                }

                public void setModifyTime(long modifyTime) {
                    this.modifyTime = modifyTime;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }
            }

            public static class TrashcleanBean {
                /**
                 * isLocalCard : false
                 * id : 133
                 * modifyTime : 1463557765295
                 * sort : 5
                 */

                private boolean isLocalCard;
                private String id;
                private long modifyTime;
                private int sort;

                public boolean isIsLocalCard() {
                    return isLocalCard;
                }

                public void setIsLocalCard(boolean isLocalCard) {
                    this.isLocalCard = isLocalCard;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public long getModifyTime() {
                    return modifyTime;
                }

                public void setModifyTime(long modifyTime) {
                    this.modifyTime = modifyTime;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }
            }

            public static class PhoneaccBean {
                /**
                 * isLocalCard : false
                 * id : 134
                 * modifyTime : 1463642588739
                 * sort : 9
                 */

                private boolean isLocalCard;
                private String id;
                private long modifyTime;
                private int sort;

                public boolean isIsLocalCard() {
                    return isLocalCard;
                }

                public void setIsLocalCard(boolean isLocalCard) {
                    this.isLocalCard = isLocalCard;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public long getModifyTime() {
                    return modifyTime;
                }

                public void setModifyTime(long modifyTime) {
                    this.modifyTime = modifyTime;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }
            }
        }
    }

    public static class ResponseHeaderBean {
        /**
         * errcode : 200
         * time : 1484721518534
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
