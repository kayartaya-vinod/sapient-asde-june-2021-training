import React from 'react';
import { useSelector } from 'react-redux';

export default function ContactCount() {
    const contacts = useSelector((store) => store.contactReducer);
    return <>You have {contacts.length} contacts.</>;
}
