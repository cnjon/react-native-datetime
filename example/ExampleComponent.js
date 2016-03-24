/**
 * Created by Kevin on 16/3/24.
 */
import React, {
    AppRegistry,
    Component,
    StyleSheet,
    TouchableOpacity,
    Text,
    View
} from 'react-native';
import DateTimePicker from 'react-native-datetime';

export default class ExampleComponent extends Component {
    constructor(props) {
        super(props);
        this.state={
            date: new Date()
        };
        this.picker = null;
    }
    showDatePicker() {
        var date = this.state.date;
        this.picker.showDatePicker(date, (d)=>{
            this.setState({date: d});
        });
    }

    showTimePicker() {
        var date = this.state.date;
        this.picker.showTimePicker(date, (d)=>{
            this.setState({date: d});
        });
    }

    showDateTimePicker() {
        var date = this.state.date;
        this.picker.showDateTimePicker(date, (d)=>{
            this.setState({date: d});
        });
    }
    render() {
        return (
            <View style={styles.container}>
                <Text style={{textAlign: 'center'}}>
                    {this.state.date.toString()}
                </Text>
                <View style={{height:40}} />
                <TouchableOpacity
                    onPress={()=>this.showDatePicker()}>
                    <Text>Show Date</Text>
                </TouchableOpacity>
                <TouchableOpacity
                    onPress={()=>this.showTimePicker()}>
                    <Text>Show Time</Text>
                </TouchableOpacity>
                <TouchableOpacity
                    onPress={()=>this.showDateTimePicker()}>
                    <Text>Show DateTime</Text>
                </TouchableOpacity>
                <DateTimePicker cancelText="Cancel" okText="OK" ref={(picker)=>{this.picker=picker}} />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center'
    }
});