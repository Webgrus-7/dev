import Header_1 from "./header";
import Header_2 from "./header2";
import { useNavigate } from 'react-router-dom';
import React, { useState } from "react";
import "./css/join.css";
import Register from "./img/register_title.png";

function Join()
{
    let text = ["web test는 어쩌고 저쩌고...아직 못 정함",<br />,
        "기반으로 한 공부하는 모두를 위한 공유 스터디서비스"]
    const [name, setName] = useState("");
    const [pw, setPw] = useState("");
    const [pwCheck, setPwCheck] = useState("");
    const [nickName, setNickName] = useState("");
    const navigate = useNavigate();
    return (
        <div className="join__outer">
            <Header_1 />
            <Header_2 />
            <div className="join__block-01">
                <img className="join__img-01" src={Register}></img>
                <span className="join__block__text-01">회원가입</span>
                <span className="join__block__text-02">Welcome!</span>
                <span className="join__block__text-03">
                    웹 서비스 이용과 회원 등록을 위해 아래의 내용을 입력해주세요.</span>
                <div className="join__block-02">
                    <span className="ID__text">아이디</span>
                    <form>
                        <input type="text" className="ID__input" onChange={(e)=>{setName(e.target.value)}}></input>
                    </form>
                    <span className="ID__text">비밀번호</span>
                    <form>
                        <input type="password" className="ID__input" onChange={(e)=>{setPw(e.target.value)}}></input>
                    </form>
                    <span className="ID__text">비밀번호 확인</span>
                    <form>
                        <input type="password" className="ID__input" onChange={(e)=>{setPwCheck(e.target.value)}}></input>
                    </form>
                    <span className="ID__text">닉네임</span>
                    <form>
                        <input type="text" className="ID__input" onChange={(e)=>{setNickName(e.target.value)}}></input>
                    </form>
                    <div className="join__block__button-01">
                        <span className="join__block__button__text" name={name} pw={pw} nickName={nickName} onClick={()=>{PW_check()}}>다음</span>
                    </div>
                </div>
            </div>
            <div className="join__block-03">
                <span className="join__block__text-04">web test</span>
                <span className="join__block__text-05">{text}</span>
            </div>
        </div>
    );
    function PW_check()
    {
        if(pwCheck!==pw)
        {
            alert("비밀번호가 일치하지 않습니다.");
        }
        else
        {
            navigate('/join2', {name:name, pw:pw, nickName:nickName});
        }
    }
}

export default Join;