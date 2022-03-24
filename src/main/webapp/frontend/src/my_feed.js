import "./css/my_feed.scss";
import Header from "./header";
import Header2 from "./header2";
import profile from "./img/profile.png"
import React, { useState } from "react";
import axios from "axios";

function MyFeed() {
  const [user, setUser] = useState([]);
  const [like, setLike] = useState([]);
  const [infoLoading, setInfoLoading] = useState(false);
  const [likeLoading, setLikeLoading] = useState(false);
  return (
      <div className="myfeed_outer" style={{overflow: 'scroll'}} onLoad={()=>{userInfo(); userLike()}}>
          <Header />
          <Header2 />
        <div className="my_info">
            <div className="my_info_profile">
                        <div className="my_info_img">
                            <img className="my_info_profile_img"src={profile}></img>
                            <div className="my_info_profile_bg"></div>
                        </div>

                        <div className="my_info_bar-01"></div>
                        <div className="my_info_block-01">
                            <div className="my_info_nikname">닉네임</div>
                            <div className="my_info_nikname_content">가치풀자</div>
                        </div>
                        <div className="my_info_bar-02"></div>
                        <div className="my_info_block_02">
                            <div className="ddaom">“</div>
                            <div className="my_info_text">한 줄 소개 멘트입니다.</div>
                        </div>
                    </div>
                    <div className="my_info_follow">
                        <div className="follow_title-bar"></div>
                            <span className="follow_title_text-01">팔로잉</span>
                            <span className="follow_title_text-02">팔로워</span>
                        <div className="follow_num-bar"></div>
                            <span className="follow_num-01">128</span>
                            <span className="follow_num-02">121444</span>
            </div>
        </div>

        <div className="myfeed">
        <div class="info">
            <div class="line1"></div>
            <span class="info_title">올린 문제</span>
            <div class="line2"></div>
            {
                infoLoading === false || likeLoading === false
                ? <span class="info_num">로딩 중...</span>
                : <span class="info_num">{user.length}</span>
            }
            <div class="boxes">
                {
                    user.length===0
                    ? null
                    : user.map((a,idx)=>{
                        return (
                                <div class="feed_box">
                                    <div class="feed_box_title">
                                    {idx+1}. {user[idx].title}<br />
                                    {user[idx].updated}
                                    </div>
                                    <div class="feed_box_by">
                                    by <span class="feed_byname">{user[idx].creator_nick}</span>
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
                                        <span>{user[idx].like_count}</span>
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
                infoLoading === false || likeLoading === false
                ? <span class="info_num">로딩 중...</span>
                : <span class="info_num">{like.length}</span>
            }
            <div class="boxes">
                {
                    like.length===0
                    ? null
                    : like.map((b,idx)=>{
                        return (
                                <div class="feed_box">
                                    <div class="feed_box_title">
                                    {idx+1}. {like[idx].title}<br />
                                    {like[idx].updated}
                                    </div>
                                    <div class="feed_box_by">
                                    by <span class="feed_byname">{like[idx].creator_nick}</span>
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
                                        <span>{like[idx].like_count}</span>
                                        <span>99%</span>
                                        <span>{like[idx].point}</span>
                                    </div>
                                    </div>
                                </div>
                        );
                    })
                }
            </div>
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
            setLike(response.data);
            setLikeLoading(true);
            console.log(response.data);
          })
      );
  }
}
export default MyFeed;