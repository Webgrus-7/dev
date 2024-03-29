import Header1 from "./header"
import Header2 from "./header2"
import "./css/feed.scss"
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import React, { useState } from "react";

function Feed()
{ 
    let problem = useSelector((state)=>state);
    const [answer__essay, setEssay] = useState("");
    const [answer__option, setOption] = useState("");
    const [view__option, setView] = useState("");
    const navigate = useNavigate();
    const Options = [
        { name: problem.returnProblem.view_1},
        { name: problem.returnProblem.view_2},
        { name: problem.returnProblem.view_3},
        { name: problem.returnProblem.view_4},
        { name: problem.returnProblem.view_5},
      ];
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
                        <div className="feed__table__question">
                            <div dangerouslySetInnerHTML={{ __html: problem.returnProblem.content }}></div>
                        </div>
                        <div className="feed__table__option">
                        {
                            Number(problem.returnProblem.type)===1
                            ? null
                            : <>
                                {Options.map((option,idx) => (
                                    <div key={option.name}>
                                    <input
                                    className="feed__option"
                                    type="radio"
                                    name="option__selector"
                                    id={option.name}
                                    value={option.name}
                                    onChange={(e)=>{setOption(idx+1); setView(e.target.value)}}
                                    />
                                    <label htmlFor={option.name}>{option.name}</label>
                                    </div>
                                ))}
                            </>
                        }
                        </div>
                    </div>
                </div>
                <div className="feed__table__button" style={{cursor:"pointer"}} onClick={()=>{check_answer()}}>
                    <span className="feed__table__button__text">제출</span>
                </div>
                <div className="feed__bar-05"></div>
                <div className="feed__answer__input">
                    <div className="feed__answer">
                        <span className="feed__answer__bar"></span>
                        <span className="feed__answer__title">정답</span>
                    </div>
                    <input className="feed__answer__essay" type="text" 
                        onChange={
                            Number(problem.returnProblem.type)===1
                            ? (e)=>{setEssay(e.target.value); setView(e.target.value)}
                            : (e)=>{setOption(e.target.value)}
                        }
                        value={
                            Number(problem.returnProblem.type)===1
                            ? null
                            : answer__option
                        }>
                    </input>
                </div>
            </div>
        </div>
    );
    function check_answer()
    {
        let correct = false;
        if(answer__essay =="" && answer__option ==""){
            alert("답을 입력해주세요.");
        }
        else
        {
            if(problem.returnProblem.type===1)
            {
                if(answer__essay===problem.returnProblem.view_1)
                {
                    correct=true;
                }
                else
                {
                    correct=false;
                }
            }
            else
            {
                if(answer__option===problem.returnProblem.answer)
                {
                    correct=true;
                }
                else
                {
                    correct=false;
                }
            }
            navigate('/feed3', {state:{correct:correct, view:view__option}});
        }
    }
}
export default Feed;