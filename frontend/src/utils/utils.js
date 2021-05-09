const getColor = (color, theme) => {
  switch (color) {
    case 'secondary':
      return theme.color.secondary;
    case 'success':
      return theme.color.success;
    case 'info':
      return theme.color.info;
    case 'warning':
      return theme.color.warning;
    case 'danger':
      return theme.color.danger;
    default:
      return theme.color.primary;
  }
};

export default getColor;
