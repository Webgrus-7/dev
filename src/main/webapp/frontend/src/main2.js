import React from "react";
import Header from "./header";
import Header2 from "./header2";
import "./css/main2.scss";
import main2book from "./img/main2book.png";
import ddaom1 from "./img/ddaom1.png";
import main2heart from "./img/main2heart.png";
import background3 from "./img/background3.png";
import { Link } from "react-router-dom";

function main2() {
  return (
    <div className="all" style={{overflowY: 'scroll'}}>
        <Header />
        <Header2 />
      <div className="main1">
        <div class="sub1">
          <img src={main2book} className="book"></img>
          <img src={ddaom1} className="ddaom"></img>
          <div className="title1">
            추천<span className="title1_2"> 분야</span>
          </div>
          <div className="text1">: 가볍게 훑어보길 추천하는 분야</div>
        </div>
        <div class="sub2">
          <div class="tip">Tip!</div>
          <div class="tip_text">
            임시로 작성한 팁... <br />
            무슨 내용 할 지 아직 <br />
            고민 중 입니다..........임시
            <br /> 임시 팁. 고민 중.. 임시 팁.
            <br />
            <br /> 임시로 작성할 팁입니다. 고민 중
          </div>
        </div>
        <div class="sub3">
          <div className="circle-01">
            <span className="circle__text__number-01">1</span>
            <span className="circle__text__subject">토익</span>
          </div>
          <div className="circle-02">
            <span className="circle__text__number-02">2</span>
            <span className="circle__text__subject">토플</span>
          </div>
          <div className="circle-03">
            <span className="circle__text__number-03">3</span>
            <span className="circle__text__subject">교양</span>
          </div>
          <div className="circle-04">
            <span className="circle__text__number-04">4</span>
            <span className="circle__text__subject">전공</span>
          </div>
          <div className="dots">
            <a></a>...
          </div>
        </div>
      </div>
      <div className="main2">
        <div class="sub4">
          <img src={main2heart} className="heart"></img>
          <img src={ddaom1} className="ddaom2"></img>
          <div className="title2">
            추천<span className="title2_2"> 피드</span>
          </div>
          <div className="text2">: 가볍게 훑어보길 추천하는 분야</div>
          <div class="tip2">
            임시로 작성한 파트 아마도 관련 설명멘트?...
            <br />
            무슨 내용 할 지 아직
            <br />
            고민 중 입니다..........임시 입니다.
          </div>
        </div>
        <div class="sub5">
          <Link to="/search">
          <input type="button" class="more" value="추천 피드 더 보기"></input>
          </Link>
          <div class="main2_boxList">
            <div class="main2_box">
              <div class="main2_box_title">
                1. 토익 추천 문제 토플 추천 문제, 전공 추천문제 <br />
                (2018.08.10)
              </div>
              <div class="main2_box_by">
                by <span class="main2_byname">조예린</span>
              </div>
              <div class="main2_box_sub">
                학습자는 I-Class 내 본인의 수업에서 ZOOM 회의 학습활동을 통해
                실시간 화상강의에 쉽게 입장할 수 있습니다.
              </div>
            </div>
            <div class="main2_box">
              <div class="main2_box_title">
                2. 토익 추천 문제 토플 추천 문제, 전공 추천문제 모음 문제 모음
                전공 족보 모음 <br />
                (2018.03.10)
              </div>
              <div class="main2_box_by">
                by <span class="main2_byname">조예린</span>
              </div>
              <div class="main2_box_sub">
                학습자는 I-Class 내 본인의 수업에서 ZOOM 회의 학습활동을 통해
                실시간 화상강의에 쉽게 입장할 수 있습니다.
              </div>
            </div>
            <div class="main2_box">
              <div class="main2_box_title">
                3. 토익 추천 문제 토플 추천 문제, 전공 추천문제 <br />
                (2018.08.10)
              </div>
              <div class="main2_box_by">
                by <span class="byname">djfla</span>
              </div>
              <div class="main2_box_sub">
                학습자는 I-Class 내 본인의 수업에서 ZOOM 회의 학습활동을 통해
                실시간 화상강의에 쉽게 입장할 수 있습니다.
              </div>
            </div>
            <div class="main2_box">
              <div class="main2_box_title">
                4. 토익 추천 문제 토플 추천 문 제, 전공 추천문제 모음집
                <br />
                (2020.08.20)
              </div>
              <div class="main2_box_by">
                by<span class="main2_byname">김주영</span>
              </div>
              <div class="main2_box_sub">
                학습자는 I-Class 내 본인의 수업에서 ZOOM 회의 학습활동을 통해
                실시간 화상강의에 쉽게 입장할 수 있습니다.
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="main3">
        <div class="sub6">
          <span class="tag">#</span>
          <img src={ddaom1} className="ddaom"></img>
          <div className="title3">
            추천<span className="title3_2"> 태그</span>
          </div>
          <div className="text3">: 팔로우 정보를 기반으로 추천하는 태그</div>
        </div>
        <div class="sub7">
          <div class="hashtag">
            <ul>
              <li>#토플어쩌고</li>
              <li>#토플어쩌고</li>
              <li>#토익전공 등</li>
              <li>#토플 연습문제</li>
            </ul>
            <ul class="etcs">
              <li>#전공 교양 족보 등</li>
              <li>#교양 등 기타</li>
              <li>#전공 교양 족보 등</li>
              <li class="etc">. . .</li>
            </ul>
            <ul>
              <li>#토플어쩌고</li>
              <li>#토플어쩌고</li>
              <li>#토익전공 등</li>
              <li>#임시 해시태그</li>
            </ul>
          </div>
        </div>
        <div class="line"></div>
        <div class="tip3_box">
          <div class="tip3">Tip!</div>
          <div class="tip3_text">
            임시로 작성한 팁... <br />
            무슨 내용 할 지 아직 <br />
            고민 중 입니다..........임시입니다.
          </div>
        </div>
      </div>
    </div>
  );
}
export default main2;