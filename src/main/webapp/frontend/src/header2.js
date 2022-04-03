import "./css/header2.scss";
import menu_q from "./img/menu_q.png";
import { Link, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

function Header2() {
  let isLogin = useSelector((state)=>state);
  let navigate = useNavigate();
  return (
    <div className="login__outer-02">
      <div className="menu-02">
        <Link to="/following_feed">
        <span className="feed__text-04">피드</span>
        </Link>
        <Link to="/search">
        <img src={menu_q} className="q"></img>
        </Link>
        <span className="feed__text-05" style={{cursor:"pointer"}} onClick={myFeedAccess}>내 피드</span>
      </div>
    </div>
  );
  function myFeedAccess()
  {
    if(isLogin.checkLogin===false)
    {
      alert("로그인 후 이용 가능합니다.");
    }
    else
    {
      navigate("/my_feed");
    }
  }
}
export default Header2;