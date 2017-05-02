package com.example.wlwlxgg.mymusic.http.result;

import java.util.List;

/**
 * Created by wlwlxgg on 2017/4/16.
 * 搜索结果对象
 */
public class SearchResult {
    /**
     * error_code : 22000
     * result : {"tag_info":{"total":0},"syn_words":"","topic_info":{"total":0},"query":"海阔天空","user_info":{"total":3089},"album_info":{"total":0},"rqt_type":3,"song_info":{"song_list":[{"resource_type_ext":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"versions":"现场","toneid":"0","info":"","has_mv":0,"album_title":"我是歌手第三季 第五期","content":"","piao_id":"0","artist_id":"362","lrclink":"http://musicdata.baidu.com/data2/lrc/ccd3de2b08c00c897538d3ec694f7d8c/268425163/268425163.lrc","data_source":0,"relate_status":0,"learn":0,"album_id":"268425166","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"64,128,256,320","title":"海阔天空","has_mv_mobile":0,"author":"韩红","pic_small":"http://musicdata.baidu.com/data2/pic/79a710b6011dfd2ea29de39d7a0f5c2c/268425273/268425273.jpg@s_0,w_90","song_id":"268425156","all_artist_id":"362","ting_uid":"1219"},{"resource_type_ext":"0","resource_type":0,"mv_provider":"0100000000","del_status":"0","havehigh":2,"versions":"","toneid":"0","info":"","has_mv":1,"album_title":"未来的第一站","content":"","piao_id":"0","artist_id":"1951","lrclink":"http://musicdata.baidu.com/data2/lrc/239768878/239768878.lrc","data_source":0,"relate_status":0,"learn":0,"album_id":"8735227","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"海阔天空","has_mv_mobile":1,"author":"林育群","pic_small":"http://musicdata.baidu.com/data2/pic/88836341/88836341.jpg@s_0,w_90","song_id":"8877990","all_artist_id":"1951","ting_uid":"10616"}],"total":31},"playlist_info":{"total":1586},"artist_info":{"artist_list":[{"ting_uid":"2345733","song_num":0,"country":"中国","avatar_middle":"http://musicdata.baidu.com/data2/music/5F1741B07058A32998B93B4DE698450B/252837196/252837196.jpg@s_0,w_120","album_num":2,"artist_desc":"","author":"<em>海阔天空<\/em>","artist_source":"web","artist_id":"13915647"},{"ting_uid":"87930765","song_num":0,"country":"","avatar_middle":"http://musicdata.baidu.com/data2/music/F63F1C4EDB9DC6C48DDC5374F3D91B35/253222311/253222311.jpg@s_0,w_120","album_num":1,"artist_desc":"","author":"<em>海阔天空<\/em>组合","artist_source":"web","artist_id":"15378862"}],"total":2}}
     */
    private Long id;
    private int error_code;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * tag_info : {"total":0}
         * syn_words :
         * topic_info : {"total":0}
         * query : 海阔天空
         * user_info : {"total":3089}
         * album_info : {"total":0}
         * rqt_type : 3
         * song_info : {"song_list":[{"resource_type_ext":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"versions":"现场","toneid":"0","info":"","has_mv":0,"album_title":"我是歌手第三季 第五期","content":"","piao_id":"0","artist_id":"362","lrclink":"http://musicdata.baidu.com/data2/lrc/ccd3de2b08c00c897538d3ec694f7d8c/268425163/268425163.lrc","data_source":0,"relate_status":0,"learn":0,"album_id":"268425166","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"64,128,256,320","title":"海阔天空","has_mv_mobile":0,"author":"韩红","pic_small":"http://musicdata.baidu.com/data2/pic/79a710b6011dfd2ea29de39d7a0f5c2c/268425273/268425273.jpg@s_0,w_90","song_id":"268425156","all_artist_id":"362","ting_uid":"1219"},{"resource_type_ext":"0","resource_type":0,"mv_provider":"0100000000","del_status":"0","havehigh":2,"versions":"","toneid":"0","info":"","has_mv":1,"album_title":"未来的第一站","content":"","piao_id":"0","artist_id":"1951","lrclink":"http://musicdata.baidu.com/data2/lrc/239768878/239768878.lrc","data_source":0,"relate_status":0,"learn":0,"album_id":"8735227","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"海阔天空","has_mv_mobile":1,"author":"林育群","pic_small":"http://musicdata.baidu.com/data2/pic/88836341/88836341.jpg@s_0,w_90","song_id":"8877990","all_artist_id":"1951","ting_uid":"10616"}],"total":31}
         * playlist_info : {"total":1586}
         * artist_info : {"artist_list":[{"ting_uid":"2345733","song_num":0,"country":"中国","avatar_middle":"http://musicdata.baidu.com/data2/music/5F1741B07058A32998B93B4DE698450B/252837196/252837196.jpg@s_0,w_120","album_num":2,"artist_desc":"","author":"<em>海阔天空<\/em>","artist_source":"web","artist_id":"13915647"},{"ting_uid":"87930765","song_num":0,"country":"","avatar_middle":"http://musicdata.baidu.com/data2/music/F63F1C4EDB9DC6C48DDC5374F3D91B35/253222311/253222311.jpg@s_0,w_120","album_num":1,"artist_desc":"","author":"<em>海阔天空<\/em>组合","artist_source":"web","artist_id":"15378862"}],"total":2}
         */

        private TagInfoBean tag_info;
        private String syn_words;
        private TopicInfoBean topic_info;
        private String query;
        private UserInfoBean user_info;
        private AlbumInfoBean album_info;
        private int rqt_type;
        private SongInfoBean song_info;
        private PlaylistInfoBean playlist_info;
        private ArtistInfoBean artist_info;

        public TagInfoBean getTag_info() {
            return tag_info;
        }

        public void setTag_info(TagInfoBean tag_info) {
            this.tag_info = tag_info;
        }

        public String getSyn_words() {
            return syn_words;
        }

        public void setSyn_words(String syn_words) {
            this.syn_words = syn_words;
        }

        public TopicInfoBean getTopic_info() {
            return topic_info;
        }

        public void setTopic_info(TopicInfoBean topic_info) {
            this.topic_info = topic_info;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public AlbumInfoBean getAlbum_info() {
            return album_info;
        }

        public void setAlbum_info(AlbumInfoBean album_info) {
            this.album_info = album_info;
        }

        public int getRqt_type() {
            return rqt_type;
        }

        public void setRqt_type(int rqt_type) {
            this.rqt_type = rqt_type;
        }

        public SongInfoBean getSong_info() {
            return song_info;
        }

        public void setSong_info(SongInfoBean song_info) {
            this.song_info = song_info;
        }

        public PlaylistInfoBean getPlaylist_info() {
            return playlist_info;
        }

        public void setPlaylist_info(PlaylistInfoBean playlist_info) {
            this.playlist_info = playlist_info;
        }

        public ArtistInfoBean getArtist_info() {
            return artist_info;
        }

        public void setArtist_info(ArtistInfoBean artist_info) {
            this.artist_info = artist_info;
        }

        public static class TagInfoBean {
            /**
             * total : 0
             */

            private int total;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class TopicInfoBean {
            /**
             * total : 0
             */

            private int total;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class UserInfoBean {
            /**
             * total : 3089
             */

            private int total;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class AlbumInfoBean {
            /**
             * total : 0
             */

            private int total;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class SongInfoBean {
            /**
             * song_list : [{"resource_type_ext":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"versions":"现场","toneid":"0","info":"","has_mv":0,"album_title":"我是歌手第三季 第五期","content":"","piao_id":"0","artist_id":"362","lrclink":"http://musicdata.baidu.com/data2/lrc/ccd3de2b08c00c897538d3ec694f7d8c/268425163/268425163.lrc","data_source":0,"relate_status":0,"learn":0,"album_id":"268425166","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"64,128,256,320","title":"海阔天空","has_mv_mobile":0,"author":"韩红","pic_small":"http://musicdata.baidu.com/data2/pic/79a710b6011dfd2ea29de39d7a0f5c2c/268425273/268425273.jpg@s_0,w_90","song_id":"268425156","all_artist_id":"362","ting_uid":"1219"},{"resource_type_ext":"0","resource_type":0,"mv_provider":"0100000000","del_status":"0","havehigh":2,"versions":"","toneid":"0","info":"","has_mv":1,"album_title":"未来的第一站","content":"","piao_id":"0","artist_id":"1951","lrclink":"http://musicdata.baidu.com/data2/lrc/239768878/239768878.lrc","data_source":0,"relate_status":0,"learn":0,"album_id":"8735227","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"海阔天空","has_mv_mobile":1,"author":"林育群","pic_small":"http://musicdata.baidu.com/data2/pic/88836341/88836341.jpg@s_0,w_90","song_id":"8877990","all_artist_id":"1951","ting_uid":"10616"}]
             * total : 31
             */

            private int total;
            private List<SongListBean> song_list;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<SongListBean> getSong_list() {
                return song_list;
            }

            public void setSong_list(List<SongListBean> song_list) {
                this.song_list = song_list;
            }

            public static class SongListBean {
                /**
                 * resource_type_ext : 0
                 * resource_type : 0
                 * mv_provider : 0000000000
                 * del_status : 0
                 * havehigh : 2
                 * versions : 现场
                 * toneid : 0
                 * info :
                 * has_mv : 0
                 * album_title : 我是歌手第三季 第五期
                 * content :
                 * piao_id : 0
                 * artist_id : 362
                 * lrclink : http://musicdata.baidu.com/data2/lrc/ccd3de2b08c00c897538d3ec694f7d8c/268425163/268425163.lrc
                 * data_source : 0
                 * relate_status : 0
                 * learn : 0
                 * album_id : 268425166
                 * bitrate_fee : {"0":"129|-1","1":"-1|-1"}
                 * song_source : web
                 * is_first_publish : 0
                 * cluster_id : 0
                 * charge : 0
                 * copy_type : 1
                 * korean_bb_song : 0
                 * all_rate : 64,128,256,320
                 * title : 海阔天空
                 * has_mv_mobile : 0
                 * author : 韩红
                 * pic_small : http://musicdata.baidu.com/data2/pic/79a710b6011dfd2ea29de39d7a0f5c2c/268425273/268425273.jpg@s_0,w_90
                 * song_id : 268425156
                 * all_artist_id : 362
                 * ting_uid : 1219
                 */

                private String resource_type_ext;
                private int resource_type;
                private String mv_provider;
                private String del_status;
                private int havehigh;
                private String versions;
                private String toneid;
                private String info;
                private int has_mv;
                private String album_title;
                private String content;
                private String piao_id;
                private String artist_id;
                private String lrclink;
                private int data_source;
                private int relate_status;
                private int learn;
                private String album_id;
                private String bitrate_fee;
                private String song_source;
                private int is_first_publish;
                private int cluster_id;
                private int charge;
                private String copy_type;
                private String korean_bb_song;
                private String all_rate;
                private String title;
                private int has_mv_mobile;
                private String author;
                private String pic_small;
                private String song_id;
                private String all_artist_id;
                private String ting_uid;

                public String getResource_type_ext() {
                    return resource_type_ext;
                }

                public void setResource_type_ext(String resource_type_ext) {
                    this.resource_type_ext = resource_type_ext;
                }

                public int getResource_type() {
                    return resource_type;
                }

                public void setResource_type(int resource_type) {
                    this.resource_type = resource_type;
                }

                public String getMv_provider() {
                    return mv_provider;
                }

                public void setMv_provider(String mv_provider) {
                    this.mv_provider = mv_provider;
                }

                public String getDel_status() {
                    return del_status;
                }

                public void setDel_status(String del_status) {
                    this.del_status = del_status;
                }

                public int getHavehigh() {
                    return havehigh;
                }

                public void setHavehigh(int havehigh) {
                    this.havehigh = havehigh;
                }

                public String getVersions() {
                    return versions;
                }

                public void setVersions(String versions) {
                    this.versions = versions;
                }

                public String getToneid() {
                    return toneid;
                }

                public void setToneid(String toneid) {
                    this.toneid = toneid;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public int getHas_mv() {
                    return has_mv;
                }

                public void setHas_mv(int has_mv) {
                    this.has_mv = has_mv;
                }

                public String getAlbum_title() {
                    return album_title;
                }

                public void setAlbum_title(String album_title) {
                    this.album_title = album_title;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getPiao_id() {
                    return piao_id;
                }

                public void setPiao_id(String piao_id) {
                    this.piao_id = piao_id;
                }

                public String getArtist_id() {
                    return artist_id;
                }

                public void setArtist_id(String artist_id) {
                    this.artist_id = artist_id;
                }

                public String getLrclink() {
                    return lrclink;
                }

                public void setLrclink(String lrclink) {
                    this.lrclink = lrclink;
                }

                public int getData_source() {
                    return data_source;
                }

                public void setData_source(int data_source) {
                    this.data_source = data_source;
                }

                public int getRelate_status() {
                    return relate_status;
                }

                public void setRelate_status(int relate_status) {
                    this.relate_status = relate_status;
                }

                public int getLearn() {
                    return learn;
                }

                public void setLearn(int learn) {
                    this.learn = learn;
                }

                public String getAlbum_id() {
                    return album_id;
                }

                public void setAlbum_id(String album_id) {
                    this.album_id = album_id;
                }

                public String getBitrate_fee() {
                    return bitrate_fee;
                }

                public void setBitrate_fee(String bitrate_fee) {
                    this.bitrate_fee = bitrate_fee;
                }

                public String getSong_source() {
                    return song_source;
                }

                public void setSong_source(String song_source) {
                    this.song_source = song_source;
                }

                public int getIs_first_publish() {
                    return is_first_publish;
                }

                public void setIs_first_publish(int is_first_publish) {
                    this.is_first_publish = is_first_publish;
                }

                public int getCluster_id() {
                    return cluster_id;
                }

                public void setCluster_id(int cluster_id) {
                    this.cluster_id = cluster_id;
                }

                public int getCharge() {
                    return charge;
                }

                public void setCharge(int charge) {
                    this.charge = charge;
                }

                public String getCopy_type() {
                    return copy_type;
                }

                public void setCopy_type(String copy_type) {
                    this.copy_type = copy_type;
                }

                public String getKorean_bb_song() {
                    return korean_bb_song;
                }

                public void setKorean_bb_song(String korean_bb_song) {
                    this.korean_bb_song = korean_bb_song;
                }

                public String getAll_rate() {
                    return all_rate;
                }

                public void setAll_rate(String all_rate) {
                    this.all_rate = all_rate;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getHas_mv_mobile() {
                    return has_mv_mobile;
                }

                public void setHas_mv_mobile(int has_mv_mobile) {
                    this.has_mv_mobile = has_mv_mobile;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public String getPic_small() {
                    return pic_small;
                }

                public void setPic_small(String pic_small) {
                    this.pic_small = pic_small;
                }

                public String getSong_id() {
                    return song_id;
                }

                public void setSong_id(String song_id) {
                    this.song_id = song_id;
                }

                public String getAll_artist_id() {
                    return all_artist_id;
                }

                public void setAll_artist_id(String all_artist_id) {
                    this.all_artist_id = all_artist_id;
                }

                public String getTing_uid() {
                    return ting_uid;
                }

                public void setTing_uid(String ting_uid) {
                    this.ting_uid = ting_uid;
                }
            }
        }

        public static class PlaylistInfoBean {
            /**
             * total : 1586
             */

            private int total;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class ArtistInfoBean {
            /**
             * artist_list : [{"ting_uid":"2345733","song_num":0,"country":"中国","avatar_middle":"http://musicdata.baidu.com/data2/music/5F1741B07058A32998B93B4DE698450B/252837196/252837196.jpg@s_0,w_120","album_num":2,"artist_desc":"","author":"<em>海阔天空<\/em>","artist_source":"web","artist_id":"13915647"},{"ting_uid":"87930765","song_num":0,"country":"","avatar_middle":"http://musicdata.baidu.com/data2/music/F63F1C4EDB9DC6C48DDC5374F3D91B35/253222311/253222311.jpg@s_0,w_120","album_num":1,"artist_desc":"","author":"<em>海阔天空<\/em>组合","artist_source":"web","artist_id":"15378862"}]
             * total : 2
             */

            private int total;
            private List<ArtistListBean> artist_list;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<ArtistListBean> getArtist_list() {
                return artist_list;
            }

            public void setArtist_list(List<ArtistListBean> artist_list) {
                this.artist_list = artist_list;
            }

            public static class ArtistListBean {
                /**
                 * ting_uid : 2345733
                 * song_num : 0
                 * country : 中国
                 * avatar_middle : http://musicdata.baidu.com/data2/music/5F1741B07058A32998B93B4DE698450B/252837196/252837196.jpg@s_0,w_120
                 * album_num : 2
                 * artist_desc :
                 * author : <em>海阔天空</em>
                 * artist_source : web
                 * artist_id : 13915647
                 */

                private String ting_uid;
                private int song_num;
                private String country;
                private String avatar_middle;
                private int album_num;
                private String artist_desc;
                private String author;
                private String artist_source;
                private String artist_id;

                public String getTing_uid() {
                    return ting_uid;
                }

                public void setTing_uid(String ting_uid) {
                    this.ting_uid = ting_uid;
                }

                public int getSong_num() {
                    return song_num;
                }

                public void setSong_num(int song_num) {
                    this.song_num = song_num;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getAvatar_middle() {
                    return avatar_middle;
                }

                public void setAvatar_middle(String avatar_middle) {
                    this.avatar_middle = avatar_middle;
                }

                public int getAlbum_num() {
                    return album_num;
                }

                public void setAlbum_num(int album_num) {
                    this.album_num = album_num;
                }

                public String getArtist_desc() {
                    return artist_desc;
                }

                public void setArtist_desc(String artist_desc) {
                    this.artist_desc = artist_desc;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public String getArtist_source() {
                    return artist_source;
                }

                public void setArtist_source(String artist_source) {
                    this.artist_source = artist_source;
                }

                public String getArtist_id() {
                    return artist_id;
                }

                public void setArtist_id(String artist_id) {
                    this.artist_id = artist_id;
                }
            }
        }
    }
}
