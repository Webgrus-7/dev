import React from "react";
import "./css/header.scss";
import pencil from "./img/makeQ.png";
import { Link, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

function Header() {
  let isLogin = useSelector((state)=>state);
  let navigate= useNavigate();
  return (
    <div className="header">
      <Link to="/">
        <span className="title">가치풀자</span>
      </Link>
      <div className="menu">
        <div className="login">
          {//login 상태면 로그아웃을 표시하고, login 상태가 아니면 로그인을 표시함.
            isLogin.checkLogin===true
            ? <span className="login__text">로그아웃</span>
            : <Link to="/login">
                <span className="login__text">로그인</span>
              </Link>
          }
          <div className="bar"></div>
          <Link to="/join1">
            <span className="join__text">회원가입</span>
          </Link>
        </div>
          <button className="makeQ" onClick={problemAccess}>
            <img src={pencil} className="button__img"></img>
            <span className="button__text">문제 생성하기</span>
          </button>
      </div>
    </div>
  );
  function problemAccess()
  {
    if(isLogin.checkLogin===false)
    {
      alert("로그인 후 이용 가능합니다.");
    }
    else
    {
      navigate("/feed4");
    }
  }
}
export default Header;