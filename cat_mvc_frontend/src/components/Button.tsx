import React from 'react';

type ButtonProps = React.ButtonHTMLAttributes<HTMLButtonElement> & {
  variant?: 'primary' | 'secondary' | 'ghost';
};

export const Button: React.FC<ButtonProps> = ({ variant = 'primary', className = '', children, ...rest }) => {
  const base = 'px-3 sm:px-4 py-2 rounded font-medium text-sm sm:text-base focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-[hsl(var(--background))]';
  const variants: Record<string, string> = {
    primary: 'bg-primary text-primary-foreground hover:brightness-95 focus:ring-primary',
    secondary: 'bg-secondary text-secondary-foreground hover:brightness-95 focus:ring-secondary',
    ghost: 'bg-white/10 hover:bg-white/20 text-white border border-white/20 focus:ring-white/50',
  };
  return (
    <button className={`${base} ${variants[variant]} ${className}`} {...rest}>
      {children}
    </button>
  );
};

export default Button;
