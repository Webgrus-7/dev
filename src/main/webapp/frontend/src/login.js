import Header from "./header";
import Header2 from "./header2";
import { Link } from "react-router-dom";
import "./css/login.css";

function Login()
{
    let text = ["web test는 어쩌고 저쩌고...아직 못 정함",<br />,
    "기반으로 한 공부하는 모두를 위한 공유 스터디서비스"]
    return (
        <div className="login__outer">
            <Header />
            <Header2 />
            <div className="login__block-01">
                <span className="login__block__text-01">log in</span>
                <div className="login__block-02">
                    <span className="ID__text">ID</span>
                    <form>
                        <input type="text" className="ID__input"></input>
                    </form>
                    <span className="PW__text">PW</span>
                    <form>
                        <input type="password" className="ID__input"></input>
                    </form>
                </div>
                <div className="login__block__button">
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
}
export default Login