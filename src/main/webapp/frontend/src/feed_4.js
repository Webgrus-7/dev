import Header1 from "./header"
import Header2 from "./header2"
import "./css/feed.scss"
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import React, { useState } from "react";

function Feed()
{
    const [field, setField] = useState("");
    const [level, setLevel] = useState("");
    const [type, setType] = useState("주관식");
    const [title, setTitle] = useState("");
    const [content, setContent] = useState(["","","","",""]);
    const [view, setView] = useState(["","","","",""])
    const [answer, setAnswer] = useState("");
    const [multipleCount, setCount] = useState(5);
    return (
        <div className="feed__outer-01" style={{overflow: 'scroll'}}>
            <Header1 />
            <Header2 />
            <div className="feed__inner-03" style={type==="주관식"?{height:'1023px'}:{height:'1320px'}}>
                <div>
                    <span className="feed__question__text-01">분야</span>
                    <span className="feed__question__text-01"  style={{marginLeft:'30px'}}>난이도</span>
                    <span className="feed__question__text-01"  style={{width:'100px', marginLeft:'18px'}}>문제 유형</span>
                </div>
                <select className="feed__question__input-01" onChange={(e)=>setField(e.target.value)}>
                    <option>토익</option>
                    <option>토플</option>
                    <option>교양</option>
                    <option>전공</option>
                    <option>기타</option>
                </select>
                <select className="feed__question__input-01" style={{width:'217px', marginLeft:'-489px'}} onChange={(e)=>setLevel(e.target.value)}>
                <option>★☆☆☆☆</option>
                <option>★★☆☆☆</option>
                <option>★★★☆☆</option>
                <option>★★★★☆</option>
                <option>★★★★★</option>
                </select>
                <select className="feed__question__input-01" style={{width:'217px', marginLeft:'-488px'}} onChange={(e)=>setType(e.target.value)}>
                <option>주관식</option>
                <option>객관식</option>
                </select>
                <div style={{marginTop:'15px'}}>
                    <span className="feed__question__text-01" onChange={(e)=>setTitle(e.target.value)}>제목</span>
                </div>
                <input className="feed__question__input-02" type="text"></input>
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
                    type==="주관식"
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
                </div>
                <div>
                <input className="feed__question__input-01" style={{marginLeft:'590px'}} type="text"></input>
                </div>
                <div className="feed__question__button-01" onClick={(()=>{console.log(content)})}>글 작성</div>
                <div className="feed__question__button-02" style={{marginTop: '0px', marginLeft:'20px'}}>취소</div>
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
    }
    function subCount()
    {
        if((multipleCount-1)<2)
        {
            return multipleCount;
        }
        else
        {
            return multipleCount-1;
        }
    }
    function viewInput(e,idx)
    {
        let newView=[...view];
        newView[idx]=e;
        return newView;
    }
}

export default Feed;