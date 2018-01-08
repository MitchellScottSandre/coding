import React, { Component } from 'react';
import { Text } from 'react-native';
import { connect } from 'react-redux';
 
class LibraryList extends Component {
    render() {
        console.log(this.props);
        return <Text>asdf</Text>;
    }
}

const mapStateToProps = state => {
    return { libraries: state.libraries }
};

export default connect(mapStateToProps)(LibraryList);