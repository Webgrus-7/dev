import axios from "axios";
import React, {useState} from "react";
import "./css/search.scss";
import Header1 from "./header";
import Header2 from "./header2";
import searchbttn from "./img/searchbttn.png";
import loading from "./img/loading.gif"
import full_heart from "./img/full_heart.png";
import empty_heart from "./img/empty_heart.png";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import moment from 'moment';
import 'moment/locale/ko';

let problemSet=[];
let problemIdx=[];
let randNum=[];
let point=1;
let field="토익";
let mapIdx=4;
let firstOpen = true;
function Search() {
  const [isLoading, setLoading] = useState(true);
  const [like, setLike] = useState([]);
  const [heart, setHeart] = useState(false);
  const toggleHeart = () =>{
    setHeart(previousState => !previousState);
  }
  let navigate = useNavigate();
  let isLogin = useSelector((state)=>state);
  let dispatch = useDispatch();
  return (
    <div className="search">
      <Header1 />
      <Header2 />
      <div class="sort" onLoad={()=>{problemList(); point=1; field="토익"}}>
        <span className="sort_title">난이도</span>
        <select defaultValue={1} className="sort_option" onChange={(e)=>pointChange(e.target.value)}>
          <option value={1}>★☆☆☆☆</option>
          <option value={2}>★★☆☆☆</option>
          <option value={3}>★★★☆☆</option>
          <option value={4}>★★★★☆</option>
          <option value={5}>★★★★★</option>
        </select>
        <span className="sort_title">분야</span>
        <select defaultValue="토익" className="sort_option" onChange={(e)=>FieldChange(e.target.value)}>
          <option value="토익">토익</option>
          <option value="토플">토플</option>
          <option value="교양">교양</option>
          <option value="전공">전공</option>
          <option value="기타">기타</option>
        </select>
        <img src={searchbttn} class="searchbttn" style={{cursor:"pointer"}} onClick={()=>{problemList()}}></img>
      </div>
      <div className="Qlist">
        <div class="boxList">
          {
            isLoading === true
            ? <div className="loading">
              <img src={loading} style={{width:"80px"}}/><br />
              문제 로딩 중입니다...</div>
            : [...Array(mapIdx)].map((s,idx)=>{
              let problem = problemSet[problemIdx[randNum[idx]]];
              const time = moment(problem.updated).format('YYYY.MM.DD');
              return (
                <div class="box" onClick={()=>{dispatch({type:'assign', payload:{problem:problem, time:time}})}} style={{cursor:"pointer"}}>
                    <div class="box_title" onClick={() => navigate("/feed2")}
                    /*navigate: class="box"로 설정시 좋아요 버튼 누르면 바로 문제풀기로 이동
                    --> title로 수정*/>
                      
                      {idx+1}. {problem.title}<br />
                      {time}
                    </div>
                    <div class="box_by">
                      by <span class="byname">{problem.creator_nick}</span>
                    </div>
                    <div class="info">
                      <div class="line1"></div>
                      <div class="infoName">
                        <span>좋아요</span>
                        <span>정답 률</span>
                        <span>난이도</span>
                      </div>
                      <div class="line2"></div>
                      <div class="infoData">
                        <span>{like[idx]}</span>
                        <span>99%</span>
                        <span>{problem.point}</span>
                      </div>
                    </div>
                    {

                        heart === false?
                        <img src={empty_heart} className="heart"
                        onClick={(event)=>{likeEvent(problem,idx,event); toggleHeart()}}>
                        </img>
                        : <img src={full_heart} className="heart" 
                        onClick={() => toggleHeart()} 
                        /*onClick={(event)=>{likeEvent(problem,idx,event)}}*/></img>
                    }

                     
                </div>
              )})
          }
        </div>
      </div>
    </div>
  );
  function likeEvent(problem,idx,event)
  {
    event.stopPropagation();
    if(isLogin===false)
    {
      alert("로그인 후 이용 가능합니다.");
    }//로그인 상태가 아닐 때 경고창 표시
    return(
      axios.post("/problem/like/"+problem.id)
      .then((response)=>{
        if(response.data===1)
          {
            problem.like_count+=1;
          }
          else
          {
            problem.like_count-=1;
          }
            let newLike=[...like];
            newLike[idx]=problem.like_count;
            setLike(newLike);
      })
    );
  }
  function pointChange(e)
  {
    point=e;
    return point;
  }//point 값 변경
  function FieldChange(e)
  {
    field=e;
    return field;
  }//field 값 변경
  function problemList()
  {
    setLoading(true);
    let count=0;
    let newLike=[...like];
    if(firstOpen === false)
    {
      problemSet=[];
      problemIdx=[];
      randNum=[];
      mapIdx=4;
    }//페이지가 처음 렌더링 된 것이 아니라면 값 초기화
    return (
      axios.get("/problem/all")
      .then((response)=>{
          for(var i=0; i<response.data.length; i++)
          {
            if(response.data[i].point === Number(point) && response.data[i].field === field)
            {
              problemSet[i]=response.data[i];
              problemIdx[count]=i;
              count++;
            }//문제들 중 point, field가 같은 값이 있으면 problemSet에 저장, index는 problemIdx에 저장
          }
          if(count===0)
          {
            alert("검색 결과가 없습니다.");
            window.location.replace("/search");
          }//검색 결과가 없을 때 원래 페이지로 돌아감(새로고침 O)
          else
          {
            if(count<4)
            {
              mapIdx=count;
            }//count가 4 이하일 때 count된 값 만큼만 화면에 표시함
            for(i=0; i<mapIdx; i++)
            {
              let rand = Math.floor(Math.random()* (problemIdx.length)); //0부터 problemIdx의 범위를 가지는 랜덤 값 생성
              if(randNum.indexOf(rand)===-1)
              {
                randNum[i] = rand;
                if(problemSet[problemIdx[randNum[i]]].like_count==="")
                {
                  newLike[i]=0;
                }
                else newLike[i]=problemSet[problemIdx[randNum[i]]].like_count;//like변수에 값 부여
              }
              else i--;//중복 랜덤 값 방지
            }
            setLike(newLike);
            firstOpen=false;//페이지가 한번 이상 렌더링 되었음을 표시함
          }
          setLoading(false);//로딩을 끝내고 검색 결과를 표시함
        })
    );
    
  }
}
export default Search;
