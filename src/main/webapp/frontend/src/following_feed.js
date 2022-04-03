import React, {useState} from "react";
import "./css/search.scss";
import Header1 from "./header";
import Header2 from "./header2";
import full_heart from "./img/full_heart.png";
import empty_heart from "./img/empty_heart.png";
import { Link, useNavigate } from "react-router-dom";

function FollowingFeed() {
  const [heart, setHeart] = useState(false);
  const toggleHeart = () =>{
    setHeart(previousState => !previousState);
  }
  return (
    <div className="following_feed">
      <Header1 />
      <Header2 />
      <div className="follwoing_feed_box">
        <div className="following_feed_box-text">
          <span className="following_feed_box-text-01">추천 피드</span>
          <span className="following_feed_box-text-02">:내가 팔로우 한 유저의 새 문제</span>
        </div>
      </div>
      <div className="Qlist">
        <div class="boxList">
          <div class="box">
            <div class="box_title">
              1. 토익 추천 문제 토플 추천 문제, 전공 추천문제 <br />
              (2018.08.10)
            </div>
            <Link to="/feed1">
              <div class="box_by">
                by <span class="byname">조예린</span>
              </div>
            </Link>
            <div class="info">
              <div class="line1"></div>
              <div class="infoName">
                <span>좋아요</span>
                <span>정답 률</span>
                <span>난이도</span>
              </div>
              <div class="line2"></div>
              <div class="infoData">
                <span>128</span>
                <span>99%</span>
                <span>☆★★★★</span>
              </div>
              {
                heart === false?
                <img src={empty_heart} className="heart"
                onClick={()=>{/*likeEvent(problem,idx,event);*/ toggleHeart()}}>
                </img>
                : <img src={full_heart} className="heart" 
                onClick={() => toggleHeart()} 
                /*onClick={(event)=>{likeEvent(problem,idx,event)}}*/></img>
              }
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default FollowingFeed;