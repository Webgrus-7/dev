import Header1 from "./header"
import Header2 from "./header2"
import "./css/feed.scss"
import profile from "./img/profile.png"
import { Link } from "react-router-dom";
let text_title = ["토익 추천 문제 토플 추천 문제, 전공 추천문제"]
function Feed()
{
    return (
        <div className="feed__outer-01">
            <Header1 />
            <Header2 />
            <div className="feed__inner-01">
                <div className="feed__block-01">
                    <div className="feed__profile-01"></div>
                    <img className="feed__profile__img-01" src={profile}></img>
                    <div className="feed__bar-01"></div>
                    <div className="feed__profile__text-block">
                        <span className="feed__profile__text-01">닉네임</span>
                        <span className="feed__profile__text-02">가치풀자</span>
                        <div className="feed__profile__button">
                            <span className="feed__profile__button-follow">팔로우</span>
                        </div>
                    </div>

                </div>
                <div className="feed__bar-02"></div>
                <div className="feed__block-02">
                    <div className="feed__block-03">
                        <span className="ddaom-01">“</span>
                        <span className="feed__profile__text-05">한 줄 소개 멘트입니다.</span>
                        <div className="feed__bar-03"></div>
                            <span className="feed__profile__text-06">팔로잉</span>
                            <span className="feed__profile__text-07">팔로워</span>
                        <div className="feed__bar-04"></div>
                            <span className="feed__profile__text-08">128</span>
                            <span className="feed__profile__text-09">121444</span>
                        </div>
                    <div className="feed__block-03">
                        <div className="feed__profile__detail-01">
                            <Link to="/feed2">
                            <span className="feed__profile__detail__text-01">{text_title}</span>
                            </Link>
                            <div class="user_data_info">
                                    <div class="user_line1"></div>
                                    <div class="user_infoName">
                                        <span>좋아요</span>
                                        <span>정답 률</span>
                                        <span>난이도</span>
                                    </div>
                                    <div class="user_line2"></div>
                                    <div class="user_infoData">
                                        <span>88</span>
                                        <span>99%</span>
                                        <span>★★★☆☆</span>
                                    </div>
                            </div>
                        </div>
                        <div className="feed__profile__detail-02">
                            <Link to="/feed2">
                            <span className="feed__profile__detail__text-01">{text_title}</span>
                            </Link>
                        </div>
                        <div className="feed__profile__detail-02">
                            <Link to="/feed2">
                            <span className="feed__profile__detail__text-01">{text_title}</span>
                            </Link>
                        </div>
                        <div className="feed__profile__detail-02">
                            <Link to="/feed2">
                            <span className="feed__profile__detail__text-01">{text_title}</span>
                            </Link>
                        </div>
                        <div className="user__question__button">
                            <span className="user__question__button-more">더 보기</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default Feed;