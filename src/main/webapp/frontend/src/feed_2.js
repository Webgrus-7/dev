import Header1 from "./header"
import Header2 from "./header2"
import "./css/feed.scss"
import profile from "./img/profile.png"
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
function Feed()
{
    let problem = useSelector((state)=>state)
    let navigate = useNavigate();
    return (
        <div className="feed__outer-01">
            <Header1 />
            <Header2 />
            <div className="feed__inner-02">
                <div>
                    <div className="feed__table-01">
                        <div>
                            <span className="feed__table__subject">{problem.returnProblem.title}</span>
                        </div>
                        <span className="feed__table__writer-01">작성자</span>
                        <span className="feed__table__writer-02">{problem.returnProblem.creator_nick}</span>
                        <span className="feed__table__writer-01">작성일</span>
                        <span className="feed__table__writer-02">{problem.returnProblem.updated}</span>
                    </div>
                    <div className="feed__table-02">
                        <div dangerouslySetInnerHTML={{ __html: problem.returnProblem.content }}></div>
                    </div>
                </div>
                <div className="feed__table__button" style={{cursor:"pointer"}} onClick={solveAccess}>
                    <span className="feed__table__button__text">풀기</span>
                </div>
                <div className="feed__bar-05"></div>
                <div className="feed__profile-02"></div>
                <img className="feed__profile__img-02" src={profile}></img>
                <span className="ddaom-02">“</span>
                <span className="feed__profile__text-010">한 줄 소개 멘트입니다.</span>
                <div className="feed__bar-06"></div>
                <span className="feed__profile__text-011">닉네임</span>
                <span className="feed__profile__text-012">{problem.returnProblem.creator_nick}</span>
            </div>
        </div>
    );
    function solveAccess()
    {
        if(problem.checkLogin===false)
        {
            alert("로그인 후 이용 가능합니다.");
        }
        else
        {
            navigate("/feed_solve");
        }
    }
}
export default Feed;