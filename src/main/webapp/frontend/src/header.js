import React from "react";
import "./css/header.scss";
import pencil from "./img/makeQ.png";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

function Header() {
  let isLogin = useSelector((state)=>state);
  return (
    <div className="header">
      <Link to="/">
        <span className="title">가치풀자</span>
      </Link>
      <div className="menu">
        <div className="login">
          {//login 상태면 로그아웃을 표시하고, login 상태가 아니면 로그인을 표시함.
            isLogin===true
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
        <Link to="/feed4">
          <button className="makeQ">
            <img src={pencil} className="button__img"></img>
            <span className="button__text">문제 생성하기</span>
          </button>
        </Link>
      </div>
    </div>
  );
}
export default Header;
