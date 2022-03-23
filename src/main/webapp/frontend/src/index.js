import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
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
let store = createStore(combineReducers({checkLogin, returnProblem}));

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);