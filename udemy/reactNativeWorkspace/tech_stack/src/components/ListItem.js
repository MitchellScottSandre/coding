import React, { Component } from 'react';
import { View, Text, TouchableWithoutFeedback, LayoutAnimation } from 'react-native';
import { connect } from 'react-redux';
import { CardSection } from './common';
import * as actions from '../actions'; // get's index.js from actions, might have many action creators 
                                       // import many things from a file at one time, do * as ____ so actions has all of them

class ListItem extends Component {
    componentWillUpdate() { // these are called Lifecycle methods
        LayoutAnimation.spring()
    }

    renderDescription() {
        const { library, expanded } = this.props;

        if (expanded) {
            // text flex 1 means don't cut it off, just wrap it around the screen
            return (
                <CardSection>
                    <Text style={{flex: 1, padding: 5}}>{library.description}</Text>
                </CardSection>
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
                    </CardSection>
                    {this.renderDescription()}
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

const mapStateToProps = (state, ownProps) => {
    const expanded = state.selectedLibraryId === ownProps.library.id
    return { 
        expanded: expanded 
    } 
    // could also do return { expanded } // since they share the same name 
};
    // this object will show up as props to our component! 
    // selectedLibraryId is the props variable name
    // state.selectedLibraryId is what is returned from reducers/index.js, which is returned from SelectionReducer, which returns the action.payload, which returns the libaryId
    // ownProps are props passed to Component we are wrapping, ownProps == this.props

export default connect(mapStateToProps, actions)(ListItem);