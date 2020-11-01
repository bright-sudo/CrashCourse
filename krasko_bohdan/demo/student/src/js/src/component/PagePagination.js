import React from 'react';
import Conteiner from '../Conteiner'
import { Button } from 'antd';
import '../Footer.css';
const PagePagination = props => (
    <div className='pagePagination'>
        <Conteiner>
        { props.offset !== 0 ? 
            <Button  onClick={props.previousPage} >{'<'}</Button> : <Button disabled>{'<'}</Button>}
            <Button>{(props.offset / props.limit) + 1}</Button>
           { props.offset+props.limit < props.numberOfStudents  ? 
            <Button id='next' onClick={props.nextPage}>{'>'}</Button> : <Button disabled>{'>'}</Button>}
        </Conteiner>
    </div>
)
export default PagePagination;