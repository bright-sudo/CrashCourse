import React from 'react';
import { Input, Tag} from 'antd';
import {ErrorMessage } from 'formik';
import '../css/FieldInput.css'

const FieldInput = (props) => (
    <div className='fieldInput'>
        <Input 
            type={props.type}
            name={props.name} 
            onChange={props.onChange}
            onBlur={props.onBlur}
            value={props.value} 
            placeholder={props.placeholder} 
            ></Input>
        {props.errors  &&  props.touched && <Tag color='error' style={{marginTop: '10px'}}><ErrorMessage name={props.name} component="div" /></Tag>}
        
    </div>
)
export default FieldInput;