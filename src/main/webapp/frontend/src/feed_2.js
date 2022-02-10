import Header1 from "./header"
import Header2 from "./header2"
import "./css/feed.scss"
import profile from "./img/profile.png"
import { Link } from "react-router-dom";
function feed()
{
    return (
        <div className="feed__outer-01">
            <Header1 />
            <Header2 />
            <div className="feed__inner-02">
                <div>
                    <div className="feed__table-01">
                        <div>
                            <span className="feed__table__subject">제목~~문제입니당</span>
                        </div>
                        <span className="feed__table__writer-01">작성자</span>
                        <span className="feed__table__writer-02">조예린</span>
                        <span className="feed__table__writer-01">작성일</span>
                        <span className="feed__table__writer-02">2021.09.01</span>
                    </div>
                    <div className="feed__table-02"></div>
                </div>
                <div className="feed__table__button">
                    <Link to="/feed3" style={{textDecoration: 'none'}}>
                    <span className="feed__table__button__text">다음</span>
                    </Link>
                </div>
                <div className="feed__bar-05"></div>
                <div className="feed__profile-02"></div>
                <img className="feed__profile__img-02" src={profile}></img>
                <span className="ddaom-02">“</span>
                <span className="feed__profile__text-010">한 줄 소개 멘트입니다.</span>
                <div className="feed__bar-06"></div>
                <span className="feed__profile__text-011">닉네임</span>
                <span className="feed__profile__text-012">가치풀자</span>
            </div>
        </div>
    );
}
export default feed;