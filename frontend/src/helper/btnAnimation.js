const btnAnimation = (e, inactive, circleRef) => {
  if (!inactive) {
    const top = e.clientY - e.target.offsetTop;
    const left = e.clientX - e.target.offsetLeft;

    // eslint-disable-next-line no-param-reassign
    circleRef.current.style.top = `${top}px`;
    // eslint-disable-next-line no-param-reassign
    circleRef.current.style.left = `${left}px`;
    // eslint-disable-next-line no-param-reassign
    circleRef.current.style.display = 'block';

    setTimeout(() => {
      // eslint-disable-next-line no-param-reassign
      circleRef.current.style.display = 'none';
      return 0;
    }, 300);
  }
};

// eslint-disable-next-line import/prefer-default-export
export { btnAnimation };
