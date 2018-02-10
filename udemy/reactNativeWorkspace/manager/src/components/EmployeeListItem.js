import React, { Component } from 'react'
import { Text } from 'react-native'
import { CardSection } from './common'

class EmployeeListItem extends Component {
    render() {
        const { name } = this.props.employee

        return (
            <CardSection>
                <Text style={styles.titleTextStyle}>{name}</Text>
            </CardSection>
        )
    }
}

const styles = {
    titleTextStyle: {
        fontSize: 18,
        paddingLeft: 15
    }
}

export default EmployeeListItem