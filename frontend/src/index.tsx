import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';
import './components/basics/Header';
import Header from './components/basics/Header';
import Body from './components/basics/Body';

const tag = <h1>Gestão de Obras</h1>
ReactDOM.render(
  <>
    { tag }
    <Header/>
    <Body msg = "Você possui duas obras em andamento"/>
  </>,
  document.getElementById('root')
);

reportWebVitals();
