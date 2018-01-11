import React, { Component } from 'react';
import { View, Text, TouchableWithoutFeedback } from 'react-native';
import { connect } from 'react-redux';
import { CardSection } from './common';
import * as actions from '../actions'; // get's index.js from actions, might have many action creators 
                                       // import many things from a file at one time, do * as ____ so actions has all of them

class ListItem extends Component {
    renderDescription() {
        const { library, selectedLibraryId } = this.props;

        if (library.id === selectedLibraryId) {
            return (
                <Text>{library.description}</Text>
            );
        }
    }

    render() {
        const { titleStyle } = styles;
        const { id, title } = this.props.library; // destructuring

        return (
            <TouchableWithoutFeedback onPress={() => this.props.selectLibrary(id)}>
                <View>
                    <CardSection> 
                        <Text style={titleStyle} >{title}</Text>
                        {this.renderDescription()}
                    </CardSection>
                </View>
            </TouchableWithoutFeedback>
        );
    }
}

const styles = {
    titleStyle: {
        fontSize: 18,
        paddingLeft: 15
    }
};

const mapStateToProps = state => {
    return { selectedLibraryId: state.selectedLibraryId}  
    // this object will show up as props to our component! 
    // selectedLibraryId is the props variable name
    // state.selectedLibraryId is what is returned from reducers/index.js, which is returned from SelectionReducer, which returns the action.payload, which returns the libaryId
};

export default connect(mapStateToProps, actions)(ListItem);