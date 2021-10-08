import React, { useState, useEffect } from 'react';
import axios from 'axios';

import Header from './components/Header/Header';
import NewContactForm from './components/NewContactForm/NewContactForm';
import ContactList from './components/ContactList/ContactList';

export default function App() {
    // don't mutate the state directly, use the setter for the same
    const [contacts, setContacts] = useState([]);

    const fetchData = async () => {
        const resp = await axios.get('http://localhost:8080/api/contacts/');
        if (!resp) return;
        // in axios, resp.data represents the payload
        // and our payload contains the array inside a property called "content"
        setContacts(resp.data.content);
        // the above call to setContacts change the state of the component
        // change to the state triggers re-rendering of component
    };

    const deleteContact = async (id) => {
        // 1. send  a DELETE request to the rest endpoint
        await axios.delete('http://localhost:8080/api/contacts/' + id);

        // 2. delete from the state "contacts" so that DOM is updated
        // const arr = [...contacts]; // local copy of the state
        // const index = arr.findIndex((c) => c.id === id);
        // arr.splice(index, 1); // deletes array element/s at given index
        // setContacts(arr); // changes the state, and triggers re-rendering of components

        // 2. or fetch the top n records once more (second round trip to the server)
        fetchData();
    };

    const addContact = async (contact) => {
        const resp = await axios.post(
            'http://localhost:8080/api/contacts/',
            contact
        );
        console.log(resp.data);
        alert('New contact added with id: ', resp.data.id);
    };

    // if the second parameter (dependencies) is empty, the callback is called only once
    // in the beginning, when the component is rendered for the first time.
    useEffect(() => {
        fetchData();
    }, []); // equivalent of componentDidMount() lifecycle method stateful component

    return (
        <>
            <Header />
            <div className='container'>
                <div className='row'>
                    <div className='col-4 col-xs-12'>
                        <NewContactForm addContact={addContact} />
                    </div>
                    <div className='col-8 col-xs-12'>
                        <ContactList
                            contacts={contacts}
                            deleteContact={deleteContact}
                        />
                    </div>
                </div>
            </div>
        </>
    );
}
