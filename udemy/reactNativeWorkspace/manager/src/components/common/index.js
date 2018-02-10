// let's you put all of your reusable components in one file, let's you do just one import { aa, bb, cc } from /common instead of multiple imports
export * from './Button'; // import and exports the button component
export * from './Card';  // if you use this approach, can't use 'default' in the files
export * from './CardSection';
export * from './Header';
export * from './InputField';
export * from './Spinner';
export * from './Confirm'