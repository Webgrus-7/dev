import React from "react";
import profile from "./img/profile.png"
import "./css/my_feed.scss";
import Header from "./header";
import Header2 from "./header2";

function myFeed() {
  let text_tag = ["#어쩌고 #저쩌고 #어쩌고"];
  return (
      <div className="myfeed_outer" style={{overflow: 'scroll'}}>
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
            <span class="info_num">123</span>
            <div class="boxes">
            <div class="feed_box">
                <div class="feed_box_title">
                1. 토익 추천 문제 토플 추천 문제, 전공 추천문제 <br />
                (2018.08.10)
                </div>
                <div class="feed_box_by">
                by <span class="feed_byname">조예린</span>
                </div>
                <div class="feed_hashtag">{text_tag}</div>
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
                    <span>☆★★★★</span>
                </div>
                </div>
            </div>
            <div class="feed_box">
                <div class="feed_box_title">
                2. 토익 추천 문제 토플 추천 문제, 전공 추천문제 모음 문제 모음
                전공 족보 모음 <br />
                (2018.03.10)
                </div>
                <div class="feed_box_by">
                by <span class="feed_byname">조예린</span>
                </div>
                <div class="feed_hashtag">{text_tag}</div>
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
                    <span>☆★★★★</span>
                </div>
                </div>
            </div>
            <div class="feed_box">
                <div class="feed_box_title">
                3. 토익 추천 문제 토플 추천 문제, 전공 추천문제 <br />
                (2018.08.10)
                </div>
                <div class="feed_box_by">
                by <span class="feed_byname">djfla</span>
                </div>
                <div class="feed_hashtag">{text_tag}</div>
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
                    <span>☆★★★★</span>
                </div>
                </div>
            </div>
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
            <span class="info_num">123</span>
        </div>
        </div>
    </div>
  );
}
export default myFeed;