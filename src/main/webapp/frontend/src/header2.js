import "./css/header2.scss";
import menu_q from "./img/menu_q.png";
import { Link } from "react-router-dom";

function header2() {
  return (
    <div className="login__outer-02">
      <div className="menu-02">
        <Link to="/feed1">
        <span className="feed__text-04">피드</span>
        </Link>
        <Link to="/search">
        <img src={menu_q} className="q"></img>
        </Link>
        <Link to="/my_feed">
        <span className="feed__text-05">내 피드</span>
        </Link>
      </div>
    </div>
  );
}
export default header2;