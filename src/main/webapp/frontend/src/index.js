import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import axios from 'axios';
import { Provider } from 'react-redux';
import { createStore, combineReducers } from 'redux';

let initialLogin=false;
function checkLogin(isLogin=initialLogin, action)
{
  if(action.type==='login')
  {
    isLogin=true;
    return isLogin;
  }
  else
  {
    return isLogin;
  }
}
let initialProblem = [];
function returnProblem(problem=initialProblem, action)
{
  let newProblem = problem;
  if(action.type==='assign')
  {
    newProblem = action.payload.problem;
    newProblem.updated = action.payload.time;
    return newProblem;
  }
  else
  {
    return problem;
  }
}
let initialState = [
  {userID:"", nickname:""}
]
function returnUser(state = initialState, action)
{
  if(action.type==='nickname')
  {
    let newState = [...state];
    newState.userID = action.payload.userID;
    axios.get("/user/"+newState.userID,
    {
      params: {
        userID:newState.userID,
      }
    }).then((response)=>{
      newState.nickname = response.data.nickname;
      newState.intro = response.data.intro;
    })
    return newState;
  }
  else
  {
    return state;
  }
}
let store = createStore(combineReducers({checkLogin, returnProblem, returnUser}));

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);