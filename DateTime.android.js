/**
 * Created by CnJon on 16/1/21.
 */
'use strict';

import React,{
    Component,
    View,
    NativeModules
} from 'react-native';

const RCTDateTimePicker = NativeModules.DateTimePicker;

export default class DateTimePicker extends Component {
    constructor(props) {
        super(props);
    }

    showDatePicker(date, callback) {
        date = date || new Date();
        var options = {year:date.getFullYear(), month:date.getMonth(), day:date.getDate()};
        RCTDateTimePicker.showDatePicker(options, function (year, month, day) {
            date.setFullYear(year);
            date.setMonth(month);
            date.setDate(day);
            callback(date);
        });
    }

    showTimePicker(date, callback) {
        date = date || new Date();
        var options = {hour:date.getHours(), minute:date.getMinutes()};
        RCTDateTimePicker.showTimePicker(options, function (hour, minute) {
            date.setHours(hour);
            date.setMinutes(minute);
            callback(date);
        });
    }

    showDateTimePicker(date, callback) {
        date = date || new Date();
        var options = {year:date.getFullYear(), month:date.getMonth(), day:date.getDate(), hour:date.getHours(), minute:date.getMinutes()};
        RCTDateTimePicker.showDateTimePicker(options, function (year, month, day, hour, minute) {
            date.setFullYear(year);
            date.setMonth(month);
            date.setDate(day);
            date.setHours(hour);
            date.setMinutes(minute);
            callback(date);
        });
    }

    render() {
        return null;
    }
}