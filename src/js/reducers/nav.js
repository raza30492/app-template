import { NAV_ACTIVATE } from "../utils/constants";

const initialState = {
  active: false,
  itemsAdmin:[
    { path: '/dashboard', label: 'Dashboard'},
    { path: '/user', label: 'User'},
    { path: '/test', label: 'Test'}
  ],
  itemsUser: [
    { path: '/dashboard', label: 'Dashboard'},
    { path: '/test', label: 'Test'}
  ]
};

export default function nav ( state = initialState, action) {

  switch ( action.type) {
    case NAV_ACTIVATE : {
      state = {...state, active: action.payload.active};
      break;
    }
  }
  return state;
}
