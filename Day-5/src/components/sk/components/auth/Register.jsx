/* eslint-disable no-unused-vars */
/* eslint-disable react/prop-types */
import React, { useEffect, useState } from 'react'
import DefaultAvatar from '../../../../asserts/party.jpg'
import { Form, TextInput } from '../ui/form'
import { MyLink, useMyNavigate } from 'simple-react-router-x'
import { useDispatch, useSelector } from 'react-redux'
import { login, register } from '../../state/users/userslice'


export default function Register(props) {
    const [showpwd, setShowpwd] = useState(false)
    const users = useSelector(state => state.users.users)
    const user = useSelector(state => state.users.current)
    const dispatch = useDispatch()
    const navigate = useMyNavigate()


    useEffect(() => {
        if (Object.keys(user).length !== 0) navigate("/user/events")
    }, [])

    
    const handleSubmit = (e) => {
        e.preventDefault()
        const formData = new FormData(e.target)

        const isExist = users.find(user => user.email === formData.get('email')) !== undefined

        if (!isExist) {
            const user = {
                fname: null,
                lname: null,
                username: null,
                email: null,
                password: null,
                country: null,
                phone: null,
            }

            for (const pair of formData.entries()) user[pair[0]] = pair[1]
            
            dispatch(register({ ...user, fname: user.username }))
            dispatch(login({ ...user, fname: user.username }))
            
            navigate('/')
        }
    }
    

    return (
        <Form onSubmit={handleSubmit}>
            <img className='h-36 w-36 p-1 rounded-full border-2 border-green-500' src={DefaultAvatar} alt="default" />
            <TextInput name='username' type="text" placeholder='Username' required />
            <TextInput name='email' type="email" placeholder='Email' required />
            <TextInput name='password' type={!showpwd ? "password" : "text"} placeholder='Password' required />

            <div className='flex space-x-4 w-full items-center'>
                <input type="checkbox" name="show-pwd" id="show-pwd" onClick={() => setShowpwd(prev => !prev)} />
                <label htmlFor="show-pwd" className='capitalize text-neutral-400 text-sm'>show password</label>
            </div>

            <div className='flex flex-col space-y-6 items-center'> 
                <button className='w-[280px] p-1 text-center bg-primary hover:bg-primary-hover py-2 px-6 text-primary-text capitalize rounded-md' type='submit'>
                    <span className='text-lg'>Register</span>
                </button>
                <MyLink to={'/auth/login'}>
                    <span className='no-underline text-white hover:text-primary-hover hover:underline text-sm'>Already have an account?</span>
                </MyLink>
            </div>
        </Form>
    )
}
