import Header1 from "./header"
import Header2 from "./header2"
import "./css/feed.scss"
import { Link } from "react-router-dom";


function Feed()
{
    return (
        <div className="feed__outer-01" style={{overflow: 'scroll'}}>
            <Header1 />
            <Header2 />
            <div className="feed__inner-02">
                <div className="feed__bar-03" style={{marginLeft: '-90px', width: '571.4px'}}></div>
                <span className="feed__profile__text-06" style={{marginLeft: '-570px', width: '99px'}}>내 점수</span>
                <span className="feed__profile__text-07">정답률</span>
                <span className="feed__profile__text-07">난이도</span>
                <div className="feed__bar-04" style={{marginLeft: '-582px', width: '571.4px'}}></div>
                <span className="feed__profile__text-08" style={{marginLeft: '-560px'}}>128</span>
                <span className="feed__profile__text-09">88.7%</span>
                <span className="feed__profile__text-09">★★★★☆</span>
                <div style={{marginLeft: '-576px', marginTop: '150px'}}>
                    <div className="feed__table-01">
                        <div>
                            <span className="feed__table__subject">제목~~문제입니당</span>
                        </div>
                        <span className="feed__table__writer-01">작성자</span>
                        <span className="feed__table__writer-02">조예린</span>
                        <span className="feed__table__writer-01">작성일</span>
                        <span className="feed__table__writer-02">2021.09.01</span>
                    </div>
                    <div className="feed__table-02">
                        <span className="feed__table__text-01">본문 자리입니당</span>
                    </div>
                    <div className="feed__table-03">
                        <div className="feed__bar-07"></div>
                        <span className="feed__table__text-02">정답</span>
                        <div>
                        <span className="feed__table__text-03">정답입니당</span>
                        </div>
                    </div>
                </div>
                <div className="feed__table__button" style={{marginTop: '865px', marginLeft: '102px'}}>
                        <Link to="/feed2" style={{textDecoration: 'none'}}>
                        <span className="feed__table__button__text">이전</span>
                        </Link>
                        </div>
                <div className="feed__bar-08"></div>
                <span className="ddaom-02" style={{marginTop: '0px', marginLeft: '42px'}}>“</span>
                <span className="feed__profile__text-010" style={{marginTop: '60px'}}>문제에 대한 한 마디.</span>
                <div className="feed__bar-09"></div>
                <span className="feed__table__text-04">가치풀자짱</span>
                <span className="feed__table__text-05">★★★★☆</span>
                <span className="feed__table__text-06">가치풀자 너무 좋아요 어쩌고 저쩌고 댓글창</span>
                <div className="feed__bar-09" style={{marginTop: '320px', marginLeft: '-293px'}}></div>
                <span className="feed__table__text-04" style={{marginTop: '335px'}}>가치풀자짱</span>
                <span className="feed__table__text-05" style={{marginTop: '335px'}}>★★★★☆</span>
                <span className="feed__table__text-06" style={{marginTop: '380px'}}>가치풀자 너무 좋아요 어쩌고 저쩌고 댓글창</span>
                <div className="feed__bar-09" style={{marginTop: '440px', marginLeft: '-293px'}}></div>
                <span className="feed__table__text-04" style={{marginTop: '455px'}}>가치풀자짱</span>
                <span className="feed__table__text-05" style={{marginTop: '455px'}}>★★★★☆</span>
                <span className="feed__table__text-06" style={{marginTop: '500px'}}>가치풀자 너무 좋아요 어쩌고 저쩌고 댓글창</span>
                <div className="feed__bar-09" style={{marginTop: '560px', marginLeft: '-293px'}}></div>
                <span className="feed__table__text-04" style={{marginTop: '575px'}}>가치풀자짱</span>
                <span className="feed__table__text-05" style={{marginTop: '575px'}}>★★★★☆</span>
                <span className="feed__table__text-06" style={{marginTop: '620px'}}>가치풀자 너무 좋아요 어쩌고 저쩌고 댓글창</span>
                <div className="feed__bar-09" style={{marginTop: '680px', marginLeft: '-293px'}}></div>
                <span className="feed__table__text-04" style={{marginTop: '695px'}}>가치풀자짱</span>
                <span className="feed__table__text-05" style={{marginTop: '695px'}}>★★★★☆</span>
                <span className="feed__table__text-06" style={{marginTop: '740px'}}>가치풀자 너무 좋아요 어쩌고 저쩌고 댓글창</span>
            </div>
        </div>
    );
}
export default Feed;