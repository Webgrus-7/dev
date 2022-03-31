import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import axios from 'axios';
import { Provider } from 'react-redux';
import { createStore, combineReducers } from 'redux';
import { persistReducer } from "redux-persist";
import { persistStore } from "redux-persist";
import { PersistGate } from "redux-persist/integration/react";
import storage from 'redux-persist/lib/storage/session';

axios.defaults.withCredentials = true;
const persistConfig = {
  key: "root",
  storage,
  whiteList:['checkLogin', 'loginToken', 'returnProblem', 'returnUser']
};
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
let initialToken = {
  accessToken:"",
  refreshToken:""
}
function loginToken(token = initialToken, action)
{
  if(action.type==='token')
  {
    let newToken = {
      accessToken:action.payload.accessToken,
      refreshToken:action.payload.refreshToken
    }
    return newToken;
  }
  else
  {
    return token;
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
let initialState = {nickname:"", intro:""};
function returnUser(state = initialState, action)
{
  if(action.type==='nickname')
  {
    let newState = {
      nickname:"",
      intro:""
    }
    axios.get("/user/"+action.payload.userID).then((response)=>{
      sessionStorage.setItem('nickname',response.data.nickname);
      sessionStorage.setItem('intro',response.data.intro);
    })
    return newState;
  }
  else
  {
    return state;
  }
}
const rootReducer = combineReducers({checkLogin, returnProblem, returnUser, loginToken})
const enhancedReducer = persistReducer(persistConfig, rootReducer);
const store = createStore(enhancedReducer);
const persistor = persistStore(store);

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <PersistGate loading={null} persistor={persistor}>
        <App />
      </PersistGate>
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);