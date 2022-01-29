import React from "react";
import "./css/header.css";
import pencil from "./img/makeQ.png";
import { Link } from "react-router-dom";

function header() {
  return (
    <div className="header">
      <Link to="/">
        <span className="title">가치풀자</span>
      </Link>
      <div className="menu">
        <div className="login">
          <Link to="/login">
            <span className="login__text">로그인</span>
          </Link>
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
export default header;
