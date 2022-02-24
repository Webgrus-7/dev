import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { Provider } from 'react-redux';
import { createStore } from 'redux';

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
let store = createStore(checkLogin);
ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);