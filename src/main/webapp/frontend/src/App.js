import Feed1 from "./feed_1";
import Feed2 from "./feed_2";
import Feed3 from "./feed_3";
import Feed4 from "./feed_4";
import Join from "./join";
import Join2 from "./join2";
import Join3 from "./join3";
import Login from "./login";
import Main from "./main";
import Main2 from "./main2";
import Search from "./search";
import MyFeed from "./my_feed";
import FeedSolve from "./feed_solve";
import FeedSolve from "./feed_solve";
import FollowingFeed from "./following_feed"
import { BrowserRouter, Route, Routes } from "react-router-dom"; 
import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import axios from 'axios';
function App() {
  let login = useSelector((state)=>state);
  let dispatch = useDispatch();
  const JWT_EXPIRY_TIME = 180 * 1000;
  useEffect(()=>{
    if(login.checkLogin === true)
    {
      axios.defaults.headers.common['ACCESS_TOKEN'] = login.loginToken.accessToken;
      axios.defaults.headers.common['REFRESH_TOKEN'] = login.loginToken.refreshToken;
      setTimeout(refresh, JWT_EXPIRY_TIME);
    }
  },[login.returnUser.nickname]);
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/feed1" element={<Feed1/>} />
          <Route path="/feed2" element={<Feed2/>} />
          <Route path="/feed_solve" element={<FeedSolve/>}/>
          <Route path="/feed3" element={<Feed3/>} />
          <Route path="/feed4" element={<Feed4/>} />
          <Route path="/join1" element={<Join/>} />
          <Route path="/join2" element={<Join2/>} />
          <Route path="/join3" element={<Join3/>} />
          <Route path="/login" element={<Login/>} />
          <Route path="/main2" element={<Main2/>} />
          <Route path="/search" element={<Search/>} />
          <Route path="/my_feed" element={<MyFeed/>} />
          <Route path="/following_feed" element={<FollowingFeed/>} />
          <Route path="/" element={<Main/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
  function refresh()
  {
    axios.post('/auth/accessToken').then(response=>{
      const accessToken = response.data;
      dispatch({type:"token", payload:{accessToken:accessToken.access_TOKEN, refreshToken:accessToken.refresh_TOKEN}});
      axios.defaults.headers.common['ACCESS_TOKEN'] = accessToken.access_TOKEN;
      axios.defaults.headers.common['REFRESH_TOKEN'] = login.loginToken.refreshToken;
    });
    setTimeout(refresh, JWT_EXPIRY_TIME);
  }
}

export default App;
