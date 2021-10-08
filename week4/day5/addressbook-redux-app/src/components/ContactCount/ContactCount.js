import React from 'react';
import { connect } from 'react-redux';

function ContactCount({ contacts = [] }) {
    return <>You have {contacts.length} contacts.</>;
}

export default connect((s) => ({ contacts: s.contactReducer }))(ContactCount);
