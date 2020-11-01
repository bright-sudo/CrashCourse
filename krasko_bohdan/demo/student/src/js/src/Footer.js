import React from 'react';
import Conteiner from './Conteiner';
import {Button, Avatar} from 'antd';
import './Footer.css';
import { Link } from 'react-router-dom';
const Footer = (props) => (
	
    <div className='footer'>
        <Conteiner>
            {props.numberOfStudents !== undefined ? 
                <Link to='/' onClick={() => props.fetchStudents()}>{<Avatar 
                    style={{backgroundColor: '#f56a00', marginRight: '5px'}} size='large'>{props.numberOfStudents}</Avatar>}</Link> : null}
            <Button onClick={() => props.handleAddStudentClickEvent()} type='primary'>Add new student +</Button>
            {props.isShowLess === true ? 
                <div style={{ textAlign: "right", marginTop: '-2.65em'}} onClick={() => props.showLess()}><Button >Show less</Button></div> : null
            }
            
        </Conteiner>
    </div>
);

export default Footer;