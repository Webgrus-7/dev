import "./css/my_feed.scss";
import Header from "./header";
import Header2 from "./header2";
import React, { useState } from "react";
import axios from "axios";

function MyFeed() {
  let text_tag = ["#어쩌고 #저쩌고 #어쩌고"];
  const [user, setUser] = useState([]);
  const [infoLoading, setInfoLoading] = useState(false);
  const [likeLoading, setLikeLoading] = useState(false);
  const [likeCount, setLikeCount] = useState(0);
  return (
      <div className="myfeed_outer" style={{overflow: 'scroll'}} onLoad={()=>{userInfo(); userLike()}}>
          <Header />
          <Header2 />
        <div className="myfeed">
        <div class="info">
            <div class="line1"></div>
            <span class="info_title">올린 문제</span>
            <div class="line2"></div>
            {
                infoLoading === false
                ? <span class="info_num">로딩 중...</span>
                : <span class="info_num">{user.length}</span>
            }
            <div class="boxes">
                {
                    user.map((a,idx)=>{
                        return (
                                <div class="feed_box">
                                    <div class="feed_box_title">
                                    {idx+1}. {user[idx].title}
                                    </div>
                                    <div class="feed_box_by">
                                    by <span class="feed_byname">{user[idx].creator_id}</span>
                                    </div>
                                    <div class="feed_data_info">
                                    <div class="feed_line1"></div>
                                    <div class="feed_infoName">
                                        <span>좋아요</span>
                                        <span>정답 률</span>
                                        <span>난이도</span>
                                    </div>
                                    <div class="feed_line2"></div>
                                    <div class="feed_infoData">
                                        <span>128</span>
                                        <span>99%</span>
                                        <span>{user[idx].point}</span>
                                    </div>
                                    </div>
                                </div>
                        );
                    })
                }
            </div>
        </div>
        <div class="info">
            <div class="line1"></div>
            <span class="info_title">푼 문제</span>
            <div class="line2"></div>
            <span class="info_num">123</span>
        </div>
        <div class="info">
            <div class="line1"></div>
            <span class="info_title">좋아요</span>
            <div class="line2"></div>
            {
                likeLoading === false
                ? <span class="info_num">로딩 중...</span>
                : <span class="info_num">{likeCount}</span>
            }
        </div>
        </div>
    </div>
  );
  function userInfo()
  {
      return (
        axios.get("/problem/user")
        .then((response)=>{
            setUser(response.data);
            setInfoLoading(true);
            console.log(response.data);
        })
      );
  }
  function userLike()
  {
      return (
          axios.get("/user/like/all")
          .then((response)=>{
            setLikeCount(response.data.length);
            setLikeLoading(true);
            console.log(response.data);
          })
      );
  }
}
export default MyFeed;