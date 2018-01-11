export default (state = null, action) => { // if state is undefined, then make it null
    switch (action.type) {
        case 'select_library':
            console.log(action.payload);
            return action.payload;  //payload is the library.id
        default:
            return state; // return whatever it returned the last time it ran (state that is passed it whatever was returned last time)
    }
};