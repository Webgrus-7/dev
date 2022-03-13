import Header1 from "./header"
import Header2 from "./header2"
import "./css/feed.scss"
import { useNavigate } from "react-router-dom";
import React, { useState } from "react";

function Feed()

{ 
    const [answer__essay, setEssay] = useState("");
    const [answer__option, setOption] = useState("");
    const navigate = useNavigate();
    const Options = [
        { name: '1'},
        { name: '2'},
        { name: '3'},
        { name: '4' },
        { name: '5'},
      ];
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
                    <div className="feed__table-02">
                        <div className="feed__table__question">문제 칸</div>{/*문제 본문 */}
                        <div className="feed__table__option">
                        {/*객관식일 때 답안 선택문항지 자리 
                        {
                            type==="주관식"
                            ? null
                            : 선택 문항지 코드
                            }
                        */}
                        <>
                        {Options.map((option) => (
                            <div key={option.name}>
                            <input
                            className="feed__option"
                            type="radio"
                            name="option__selector"
                            value={option.name}
                            onChange={(e)=>{setOption(e.target.value)}}
                            
                                 />
                            <label htmlFor={option.name}>{option.name}</label>
                            </div>
                        ))}
                        </>
                        </div>
                    </div>
                </div>
                <div className="feed__table__button">
                    <span className="feed__table__button__text" onClick={()=>{check_answer()}}>제출</span>
                </div>
                <div className="feed__bar-05"></div>
                <div className="feed__answer__input">
                    <div className="feed__answer">
                        <span className="feed__answer__bar"></span>
                        <span className="feed__answer__title">정답</span>
                    </div>
                    <input className="feed__answer__essay" type="text" 
                    onChange={(e)=>{setOption(e.target.value)}} value={answer__option}></input>
                    {/*type==주관식일 때, onChange={(e)=>{setEssay(e.target.value)}}로 */}
                </div>
            </div>
        </div>
    );
    function check_answer()
    {
        if(answer__essay =="" && answer__option ==""){
            alert("답을 입력해주세요.");
        }
            else
            {
                navigate('/feed3');
            }
    }
}
export default Feed;