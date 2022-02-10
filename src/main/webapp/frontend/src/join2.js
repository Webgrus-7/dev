import Header_1 from "./header";
import Header_2 from "./header2";
import "./css/join2.scss";
import axios from "axios";
import React, { useState } from "react";
import { useLocation, useNavigate } from 'react-router-dom';
import Register from "./img/register_title.png"

function Join2()
{
    const location = useLocation();
    const userID = location.state.userID;
    const password = location.state.password;
    const nickname = location.state.nickname;
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [intro, setIntro] = useState("");
    const [major, setMajor] = useState("");
    const navigate = useNavigate();
    return (
        <div className="join__outer">
            <Header_1 />
            <Header_2 />
            <div className="join__block-04">
                <img className="join__img-02" src={Register}></img>
                <span className="join__block__text-06">회원가입</span>
                <div>
                    <span className="join__main__text">
                <div>
                    <span className="join__main__text-01">가치풀자</span>
                    <span className="join__main__text-02">에서</span>
                </div>
                <div>
                    회원님을 알아가는 데에 도움이 되는 
                    <span className="join__main__text-03"> 정보를 입력해주세요.</span>
                </div>
                </span>
                </div>
                <span className="EMAIL__text">이메일</span>
                <form>
                    <input type="text" className="EMAIL__input" onChange={(e)=>{setEmail(e.target.value)}}></input>
                </form>
                <span className="EMAIL__text">휴대폰 번호</span>
                <form>
                    <input type="text" className="EMAIL__input" onChange={(e)=>{setPhone(e.target.value)}}></input>
                </form>
                <span className="EMAIL__text">한 줄 자기소개</span>
                <form>
                    <input type="text" className="EMAIL__input" onChange={(e)=>{setIntro(e.target.value)}}></input>
                </form>
                <span className="EMAIL__text">가치 풀고 싶은 분야</span>
                <form>
                    <input type="text" className="EMAIL__input" onChange={(e)=>{setMajor(e.target.value)}}></input>
                </form>
                <div className="join__block__button-02">
                    <span className="join__block__button__text" onClick={()=>{register(); check();}}>다음</span>
                </div>
            </div>
        </div>
    );
    function register() {
        return axios.post("/user/local/new",{
            userID,
            email,
            phone,
            nickname,
            password,
            major,
            intro
        })
        .then(function(response)
        {
            console.log(response);
            navigate('/join3');
        })
    }
    function check()
    {
        if(isEmail(email)===false)
        {
            alert("올바른 이메일 주소를 입력해주세요.");
        }
        else if(isCelluar(phone)===false)
        {
            alert("올바른 휴대폰 번호를 입력해주세요.");
        }
    }
    function isEmail(email) {
        var exp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        return exp.test(email); // 형식에 맞는 경우 true 리턴
    }
    function isCelluar(phone) {
        var exp = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
        return exp.test(phone); // 형식에 맞는 경우 true 리턴
    }
}
export default Join2;