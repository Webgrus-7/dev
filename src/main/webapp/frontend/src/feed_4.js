import Header1 from "./header"
import Header2 from "./header2"
import "./css/feed.scss"
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Feed()
{
    const [field, setField] = useState("토익");
    const [point, setPoint] = useState(1);
    const [type, setType] = useState(1);
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const [view, setView] = useState(["","","","",""])
    const [answer, setAnswer] = useState("");
    const [multipleCount, setCount] = useState(5);
    let navigate = useNavigate();
    return (
        <div className="feed__outer-01" style={{overflow: 'scroll'}}>
            <Header1 />
            <Header2 />
            <div className="feed__inner-03" style={type===1?{height:'1023px'}:{height:'1320px'}}>
                <div>
                    <span className="feed__question__text-01">분야</span>
                    <span className="feed__question__text-01"  style={{marginLeft:'30px'}}>난이도</span>
                    <span className="feed__question__text-01"  style={{width:'100px', marginLeft:'18px'}}>문제 유형</span>
                </div>
                <select defaultValue="토익" className="feed__question__input-01" onChange={(e)=>setField(e.target.value)}>
                    <option value="토익">토익</option>
                    <option value="토플">토플</option>
                    <option value="교양">교양</option>
                    <option value="전공">전공</option>
                    <option value="기타">기타</option>
                </select>
                <select defaultValue={1} className="feed__question__input-01" style={{width:'217px', marginLeft:'-489px'}} onChange={(e)=>setPoint(Number(e.target.value))}>
                <option value={1}>★☆☆☆☆</option>
                <option value={2}>★★☆☆☆</option>
                <option value={3}>★★★☆☆</option>
                <option value={4}>★★★★☆</option>
                <option value={5}>★★★★★</option>
                </select>
                <select defaultValue={1} className="feed__question__input-01" style={{width:'217px', marginLeft:'-488px'}} onChange={(e)=>setType(Number(e.target.value))}>
                <option value={1}>주관식</option>
                <option value={2}>객관식</option>
                </select>
                <div style={{marginTop:'15px'}}>
                    <span className="feed__question__text-01">제목</span>
                </div>
                <input className="feed__question__input-02" type="text" onChange={(e)=>setTitle(e.target.value)}></input>
                <div style={{marginTop:'15px'}}>
                    <span className="feed__question__text-01">내용</span>
                </div>
                <div className="feed__question__input-03">
                        <CKEditor
                            editor={ ClassicEditor }
                            onChange={ ( event, editor ) => {
                                const data = editor.getData();
                                setContent(data);
                            } }
                        />
                </div>
                {
                    type===1
                    ? null
                    : <ol style={{marginLeft:'40px'}}>
                        {
                            [...Array(multipleCount)].map((m,idx)=>{
                                return <li><input className="feed__question__input-02" type="text" style={{marginLeft:'10px', marginBottom:'5px', width:'265px'}}
                                onChange={(e)=>{setView(viewInput(e.target.value, idx))}}></input></li>
                            })
                        }
                        <span className="feed__question__text-02" onClick={(()=>{setCount(addCount)})}>문항 추가</span>
                        <span className="feed__question__text-02">/</span>
                        <span className="feed__question__text-02" onClick={(()=>{setCount(subCount)})}>문항 삭제</span>
                    </ol>
                }
                <div>
                <span className="feed__question__text-01" style={{marginTop: '38px', marginLeft: '580px'}}>정답</span>
                <span className="feed__question__text-02" style={{marginLeft:'-190px'}}>(객관식일 경우 번호만 작성)</span>
                </div>
                <div>
                <input className="feed__question__input-01" style={{marginLeft:'590px'}} type="text" onChange={(e)=>{setAnswer(e.target.value)}}></input>
                </div>
                <div className="feed__question__button-01" onClick={()=>{viewIsEmpty()}}>글 작성</div>
                <div className="feed__question__button-02" onClick={()=>{navigate("/")}} style={{marginTop: '0px', marginLeft:'20px'}}>취소</div>
            </div>
            
        </div>
    );
    function addCount()
    {
        if((multipleCount+1)>=6)
        {
            return multipleCount;
        }
        else
        {
            return multipleCount+1;
        }
    }//5개 이상의 문항은 만들 수 없도록 설정
    function subCount()
    {
        let newView=[...view];
        newView[multipleCount-1]="";
        setView(newView);
        if((multipleCount-1)<2)
        {
            return multipleCount;
        }
        else
        {
            return multipleCount-1;
        }
    }//2개 이하의 문항은 만들 수 없도록 설정
    function viewIsEmpty()
    {
        if(type===2)
        {
            let count = 0;
            for(var i=0; i<multipleCount; i++)
            {
                if(view[i]!=="")
                {
                    count++;
                }
                if(i===multipleCount-1)
                {
                    if(count===multipleCount)
                    {
                        return check();
                    }
                    else
                    {
                        return alert("문항을 입력해주세요.");
                    }
                }
            }
        }
        else
        {
            return check();
        }
    }//객관식일 경우 문항 개수만큼 for문을 돌아 비어있는 값이 있으면 viewEmpty 변수를 true로 만듦
    function viewInput(e,idx)
    {
        let newView=[...view];
        newView[idx]=e;
        return newView;
    }//view에 값 할당
    function check()
    {
        if(title==="")
        {
            alert("제목을 입력해주세요.");
        }
        else if(content==="")
        {
            alert("내용을 입력해주세요.");
        }
        else if(answer==="")
        {
            alert("정답을 입력해주세요.");
        }
        else
        {
            makeProblem();
        }
    }
    function makeProblem()
    {
        if(type===1 && answer!=="")
            {
                let newView=[answer,"","","",""];
                setView(newView);
                setAnswer(1);
            }
            return (
                axios.post("/problem/new",
                    {
                        answer,
                        content,
                        field,
                        point,
                        title,
                        type,
                        view_1:view[0],
                        view_2:view[1],
                        view_3:view[2],
                        view_4:view[3],
                        view_5:view[4]
                    })
                    .then((response)=>{
                        console.log(response);
                        alert("작성이 완료되었습니다.")
                        navigate("/");
                    })
            );
    }
}

export default Feed;