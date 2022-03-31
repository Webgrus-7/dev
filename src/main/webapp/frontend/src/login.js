import Header from "./header";
import Header2 from "./header2";
import { Link, useNavigate } from "react-router-dom";
import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import axios from 'axios';
import "./css/login.scss";

axios.defaults.withCredentials = true;

function Login()
{
    const navigate = useNavigate();
    let [userID, setID] = useState("");
    let [password, setPW] = useState("");
    let dispatch = useDispatch();
    let login = useSelector((state)=>state);
    let text = ["web test는 어쩌고 저쩌고...아직 못 정함",<br />,
    "기반으로 한 공부하는 모두를 위한 공유 스터디서비스"]
    return (
        <div className="login__outer">
            <Header />
            <Header2 />
            <div className="login__block-01">
                <span className="login__block__text-01">log in</span>
                <div className="login__block-02">
                    <form onKeyPress={(e)=>{EnterKey(e)}}>
                        <div><span className="ID__text-01">ID</span></div>
                        <input type="text" className="ID__input-01" onChange={(e)=>{setID(e.target.value)}}></input>
                        <div><span className="PW__text">PW</span></div>
                        <input type="password" className="ID__input-01" onChange={(e)=>{setPW(e.target.value)}}></input>
                    </form>
                </div>
                <div className="login__block__button" style={{cursor:"pointer"}} onClick={()=>{check(); onLogin()}}>
                    <span className="login__block__button__text">로그인</span>
                </div>
                <Link to="/join1">
                    <span className="login__block__text-02">가치풀자가 처음이신가요?</span>
                </Link>
            </div>
            <div className="login__block-03">
                <span className="login__block__text-03">web test</span>
                <span className="login__block__text-04">{text}</span>
            </div>
        </div>
    );
    function EnterKey(e)
    {
        if(e.key==='Enter')
        {
            check();
            onLogin();
        }
    }
    function check()
    {
        if(userID==="")
        {
            alert("아이디를 입력해주세요.");
        }
        else if(password==="")
        {
            alert("비밀번호를 입력해주세요.");
        }
    }
    function onLogin()
    {
        const JWT_EXPIRY_TIME = 180 * 1000;
        return (
            axios.post('/auth/login',
            {
                password,
                userID
            },
            ).then(response => {
                if(response.data === "존재하지 않는 회원입니다." || response.data === "비밀번호가 일치하지 않습니다.")
                {
                    alert(response.data);
                }
                else
                {
                    const accessToken = response.data;
                    axios.defaults.headers.common['ACCESS_TOKEN'] = accessToken.access_TOKEN;
                    axios.defaults.headers.common['REFRESH_TOKEN'] = accessToken.refresh_TOKEN;
                    dispatch({type:"login"});
                    dispatch({type:"token", payload:{accessToken:accessToken.access_TOKEN, refreshToken:accessToken.refresh_TOKEN}});
                    dispatch({type:"nickname", payload:{userID:userID}});
                    setTimeout(refresh, JWT_EXPIRY_TIME);
                    setTimeout(()=>{navigate("/")}, 4000);
                }
            })
        );
    }
    function refresh()
    {
        const JWT_EXPIRY_TIME = 180 * 1000;
        axios.post('/auth/accessToken').then(response=>{
        const accessToken = response.data;
        dispatch({type:"token", payload:{accessToken:accessToken.access_TOKEN, refreshToken:accessToken.refresh_TOKEN}});
        axios.defaults.headers.common['ACCESS_TOKEN'] = accessToken.access_TOKEN;
        axios.defaults.headers.common['REFRESH_TOKEN'] = accessToken.refresh_TOKEN;
        });
        setTimeout(refresh, JWT_EXPIRY_TIME);
    }
}

export default Login