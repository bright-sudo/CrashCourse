import React, {Component} from 'react';
import './App.css';
import { 
	BrowserRouter as Router,
	Route} from 'react-router-dom';
import Courses from './pages/Courses';
import Students from './pages/Students';

class App extends Component {
	render() {
		return (
		
			<Router >
				<Route exact path='/' component={Students}/>
				<Route exact path='/:studentId/courses' component={Courses}/>
			</Router>
			
		)
	}
}

export default App;
