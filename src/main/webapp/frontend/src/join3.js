import Header_1 from "./header";
import Header_2 from "./header2";
import "./css/join3.scss";
import { Link } from "react-router-dom";

function Join()
{
    return (
        <div className="join__outer">
            <Header_1 />
            <Header_2 />
            <div className="join__block-05">
                <div className="join__block-05__block">
                    <span className="Complete">Complete!</span>
                    <span className="join__complete__text">
                         <div className="join__complete__div"> 
                         회원가입이 정상적으로 완료되었습니다.
                         <div>이제 <span className="join__complete__text-01">가치풀자</span>
                         의 모든 서비스를 자유롭게 이용하실 수 있습니다.</div>
                          </div>
                    </span>   
                    <div className="join__complete__button">
                    <Link to="/">
                    <span className="join__complete__button__text">메인으로</span>
                    </Link>
                </div>     
                </div>

            </div>
        </div>
    );
}
export default Join;